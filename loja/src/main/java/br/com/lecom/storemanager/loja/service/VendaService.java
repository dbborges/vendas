package br.com.lecom.storemanager.loja.service;

import br.com.lecom.storemanager.loja.dto.*;
import br.com.lecom.storemanager.loja.entity.ItemVenda;
import br.com.lecom.storemanager.loja.entity.Loja;
import br.com.lecom.storemanager.loja.entity.Venda;
import br.com.lecom.storemanager.loja.exception.LimitePeriodoExcedidoException;
import br.com.lecom.storemanager.loja.exception.VendaNaoEncontradaException;
import br.com.lecom.storemanager.loja.mapper.ItemVendaMapper;
import br.com.lecom.storemanager.loja.mapper.VendaMapper;
import br.com.lecom.storemanager.loja.repository.ItemVendaRepository;
import br.com.lecom.storemanager.loja.repository.VendaRepository;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;
    private final LojaService lojaService;
    private final String uriProdutos;
    private final String uriOrdensEntrega;
    private final VendaMapper vendaMapper;
    private final ItemVendaMapper itemVendaMapper;

    public VendaService(VendaRepository vendaRepository,
                        ItemVendaRepository itemVendaRepository,
                        LojaService lojaService,
                        @Value("http://localhost:8080/catalogo-api/v1/produtos") String uriProdutos,
                        @Value("http://localhost:8080/transportadora-api/v1/ordensEntrega") String uriOrdensEntrega,
                        VendaMapper vendaMapper,
                        ItemVendaMapper itemVendaMapper) {
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
        this.lojaService = lojaService;
        this.uriProdutos = uriProdutos;
        this.uriOrdensEntrega = uriOrdensEntrega;
        this.vendaMapper = vendaMapper;
        this.itemVendaMapper = itemVendaMapper;
    }

    @Transactional
    public URI registrarVenda(RequisicaoRegistrarVendasDTO vendaDTO) {
        log.info("Registrando venda para a loja {}", vendaDTO.getIdLoja());

        Loja lojaEncontrada = lojaService.buscarLojaPorId(vendaDTO.getIdLoja());

        Venda vendaRegistrada = registrarDadosGeraisDaVenda(vendaDTO, lojaEncontrada);
        registraItensNaVenda(vendaRegistrada, vendaDTO.getItensVenda());

        registrarOrdemEntregaTransportadora(
                RequisicaoRegistrarOrdemEntregaDTO.builder()
                        .idVenda(vendaRegistrada.getId())
                        .cep(vendaRegistrada.getCep())
                        .logradouro(vendaRegistrada.getLogradouro())
                        .numero(vendaRegistrada.getNumero())
                        .bairro(vendaRegistrada.getBairro())
                        .cidade(vendaRegistrada.getCidade())
                        .uf(vendaRegistrada.getUf())
                        .build());

        return ServletUriComponentsBuilder
                .fromPath("/v1/vendas")
                .path("/{id}")
                .build(vendaRegistrada.getId());
    }

    public List<RespostaBuscarVendasDTO> buscarVendas(RequisicaoBuscarVendasDTO requisicao) {

        validarPeríodoLimiteDeBuscaEntreDatas(requisicao.getDataInicialDaCompra(), requisicao.getDataFinalDaCompra());

        return vendaRepository.findByDataDaCompraBetween(requisicao.getIdLoja(), requisicao.getDataInicialDaCompra(), requisicao.getDataFinalDaCompra())
                .stream()
                .map(vendaMapper::toRespostaBuscarVendasDTO)
                .collect(Collectors.toList());
    }

    public RespostaAtualizarStatusEntregaDTO atualizarStatusEntrega(RequisicaoAtualizarStatusEntregaDTO requisicao) {
        validaSeVendaExiste(requisicao.getIdVenda());
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body =  new HashMap<>();
        body.put("venda-id", requisicao.getIdVenda());
        body.put("status", requisicao.getStatus());

        ParameterizedTypeReference<RespostaAtualizarStatusEntregaDTO> responseType = new ParameterizedTypeReference<>() { };
        HttpEntity<?> httpEntity = new HttpEntity<Object>(new Gson().toJson(body), headers);
        ResponseEntity<RespostaAtualizarStatusEntregaDTO> result = restTemplate
                .exchange(uriOrdensEntrega.concat("/atualizarStatusEntrega"),
                        HttpMethod.POST, httpEntity, responseType);
        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        }
        return null;
    }

    private RespostaRegistrarOrdemEntregaDTO registrarOrdemEntregaTransportadora(RequisicaoRegistrarOrdemEntregaDTO requisicaoRegistrarOrdemEntregaDTO) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ParameterizedTypeReference<RespostaRegistrarOrdemEntregaDTO> responseType = new ParameterizedTypeReference<>() { };
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        Map<String, Object> body =  new HashMap<>();

        body.put("id-venda", requisicaoRegistrarOrdemEntregaDTO.getIdVenda().toString());
        body.put("cep", requisicaoRegistrarOrdemEntregaDTO.getCep());
        body.put("logradouro", requisicaoRegistrarOrdemEntregaDTO.getLogradouro());
        body.put("numero", requisicaoRegistrarOrdemEntregaDTO.getNumero());
        body.put("complemento", requisicaoRegistrarOrdemEntregaDTO.getComplemento());
        body.put("bairro", requisicaoRegistrarOrdemEntregaDTO.getBairro());
        body.put("cidade", requisicaoRegistrarOrdemEntregaDTO.getCidade());
        body.put("uf", requisicaoRegistrarOrdemEntregaDTO.getUf());


        HttpEntity<?> httpEntity = new HttpEntity<Object>(new Gson().toJson(body), headers);
        ResponseEntity<RespostaRegistrarOrdemEntregaDTO> result = restTemplate
                .exchange(uriOrdensEntrega, HttpMethod.POST, httpEntity, responseType);
        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        }
        return null;
    }

    private void validarPeríodoLimiteDeBuscaEntreDatas(LocalDateTime dataInicialDaCompra, LocalDateTime dataFinalDaCompra) {
        Long diasDeDiferenca = Duration
                .between(dataInicialDaCompra, dataFinalDaCompra).toDays();
        if(diasDeDiferenca > 10) {
            throw new LimitePeriodoExcedidoException(10L);
        }

    }

    private void registraItensNaVenda(Venda vendaRegistrada, List<ItemVendaDTO> itensASeremCadastrados) {
        if(vendaRegistrada.getItensVenda() == null) {
            vendaRegistrada.setItensVenda(new ArrayList<>());
        }
        for (ItemVendaDTO itemVendaDTO : itensASeremCadastrados) {
            ItemVenda itemVenda = itemVendaMapper.toModel(itemVendaDTO);
            itemVenda.setVenda(vendaRegistrada);
            vendaRegistrada.getItensVenda().add(itemVenda);
        }
        vendaRepository.saveAndFlush(vendaRegistrada);
    }

    private void adicionarItensNaVenda(Venda vendaCadastrada, Set<ItemVenda> itensASeremCadastrados) {
        itensASeremCadastrados
                .forEach(itemVenda -> vendaCadastrada.getItensVenda().add(itemVenda));

    }

    private Venda registrarDadosGeraisDaVenda(RequisicaoRegistrarVendasDTO requisicaoRegistrarVendasDTO, Loja lojaEncontrada) {
        Venda vendaASerRegistrada = Venda.builder()
                .loja(lojaEncontrada)
                        .cep(requisicaoRegistrarVendasDTO.getCep())
                .logradouro(requisicaoRegistrarVendasDTO.getLogradouro())
                .numero(requisicaoRegistrarVendasDTO.getNumero())
                .complemento(requisicaoRegistrarVendasDTO.getComplemento())
                .bairro(requisicaoRegistrarVendasDTO.getBairro())
                .cidade(requisicaoRegistrarVendasDTO.getCidade())
                .uf(requisicaoRegistrarVendasDTO.getUf())
                .dataDaCompra(LocalDateTime.now())
                .build();
        return vendaRepository.saveAndFlush(vendaASerRegistrada);
    }
    private void validaSeVendaExiste(Long idVenda) {
        vendaRepository.findById(idVenda)
                .orElseThrow(() -> {throw new VendaNaoEncontradaException(idVenda);});
    }

}