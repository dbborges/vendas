package br.com.lecom.storemanager.loja.service;

import br.com.lecom.storemanager.loja.dto.ProdutoDTO;
import br.com.lecom.storemanager.loja.dto.RequisicaoCadastrarLojaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaBuscarLojaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaCadastrarLojaDTO;
import br.com.lecom.storemanager.loja.entity.Loja;
import br.com.lecom.storemanager.loja.exception.LojaJaExisteException;
import br.com.lecom.storemanager.loja.exception.LojaNaoEncontradaException;
import br.com.lecom.storemanager.loja.mapper.LojaMapper;
import br.com.lecom.storemanager.loja.repository.LojaRepository;
import br.com.lecom.storemanager.loja.repository.VendaRepository;
import br.com.lecom.storemanager.util.RestPageImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Log4j2
public class LojaService {

    private final VendaRepository vendaRepository;
    private final LojaRepository lojaRepository;
    private final LojaMapper lojaMapper;
    private final String uriProdutos;

    public LojaService(VendaRepository vendaRepository,
                       LojaRepository lojaRepository,
                       LojaMapper lojaMapper,
                       @Value("http://localhost:8080/catalogo-api/v1/produtos") String uriProdutos) {
        this.vendaRepository = vendaRepository;

        this.lojaRepository = lojaRepository;
        this.lojaMapper = lojaMapper;
        this.uriProdutos = uriProdutos;
    }

    public Page<RespostaCadastrarLojaDTO> buscarTodos(final Pageable pageAble) {
        log.info("Buscando todas as lojas");
        final var page = lojaRepository.findAll(pageAble);
        if (page.hasContent()) {
            final var listaDePaginas = page.getContent()
                    .stream().map(RespostaCadastrarLojaDTO::new)
                    .collect(Collectors.toList());
            return new PageImpl<>(
                    listaDePaginas,
                    page.getPageable(),
                    page.getTotalElements()
            );
        }
        return Page.empty();
    }

    public RespostaBuscarLojaDTO buscarPorId(Long id) {
        log.info("Buscando loja com o id {}", id);
        final Loja lojaEncontrada = buscarLojaPorId(id);
        return lojaMapper.toDTO(lojaEncontrada);
    }

    public Boolean verificaSeExiste(Long idLoja) {
        return isLojaExiste(idLoja);
    }

    public URI cadastrar(RequisicaoCadastrarLojaDTO requisicao) {
        log.info("Cadastrando loja com o cnpj {} e razão social {}", requisicao.getCnpj(), requisicao.getRazaoSocial());
        verificaSeExistePorRazaoSocial(requisicao.getRazaoSocial());

        final Loja lojaParaCadastrar = lojaMapper.toModel(requisicao);
        final Loja lojaCadastrada = lojaRepository.save(lojaParaCadastrar);

        return ServletUriComponentsBuilder
                .fromPath("/v1/lojas")
                .path("/{id}")
                .build(lojaCadastrada.getId());
    }

    public Page<ProdutoDTO> buscarItensNaLoja(Long idLoja, Pageable pageable) {
        buscarLojaPorId(idLoja);
        RestTemplate restTemplate = new RestTemplate();
        String requisicao = uriProdutos
                    .concat(
                String.format("?direction=%s&page=%s&size=%s&sort=%s",
                        Sort.Direction.ASC,
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        "id"));
        ParameterizedTypeReference<RestPageImpl<ProdutoDTO>> responseType = new ParameterizedTypeReference<>() { };
        ResponseEntity<RestPageImpl<ProdutoDTO>> result = restTemplate
                .exchange(requisicao, HttpMethod.GET, null, responseType);
        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody().map(it -> it);
        }
        return Page.empty();
    }

    private void verificaSeExistePorRazaoSocial(final String razaoSocial) {
        lojaRepository.findByRazaoSocial(razaoSocial)
                .ifPresent(loja -> {throw new LojaJaExisteException(razaoSocial);});
    }

    public Loja buscarLojaPorId(Long idLoja) {
        return lojaRepository.findById(idLoja)
                .orElseThrow(() -> new LojaNaoEncontradaException(idLoja));
    }

    public Boolean isLojaExiste(Long idLoja) {
        AtomicReference<Boolean> isLojaExiste = new AtomicReference();
        lojaRepository.findById(idLoja).ifPresentOrElse(loja -> {
            isLojaExiste.set(true);
        }, () -> {
            isLojaExiste.set(false);
        });
    return isLojaExiste.get();
    }

}