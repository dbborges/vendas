package br.com.lecom.transportadora.transportadora.service;

import br.com.lecom.transportadora.transportadora.dto.RequisicaoAtualizarStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RequisicaoRegistrarOrdemEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RespostaStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.entity.HistoricoOrdemEntrega;
import br.com.lecom.transportadora.transportadora.entity.OrdemEntrega;
import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import br.com.lecom.transportadora.transportadora.exception.OrdemDeEntregaNaoEncontradaPorCodigoRastreamentoException;
import br.com.lecom.transportadora.transportadora.exception.OrdemDeEntregaNaoEncontradaPorIdVendaException;
import br.com.lecom.transportadora.transportadora.mapper.OrdemEntregaMapper;
import br.com.lecom.transportadora.transportadora.repository.HistoricoOrdemEntregaRepository;
import br.com.lecom.transportadora.transportadora.repository.OrdemEntregaRepository;
import br.com.lecom.transportadora.util.RastreamentoUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Log4j2
public class OrdemEntregaService {

    private final OrdemEntregaRepository ordemEntregaRepository;
    private final HistoricoOrdemEntregaRepository historicoOrdemEntregaRepository;
    private final OrdemEntregaMapper ordemEntregaMapper;

    public OrdemEntregaService(OrdemEntregaMapper ordemEntregaMapper,
                               OrdemEntregaRepository ordemEntregaRepository,
                               HistoricoOrdemEntregaRepository historicoOrdemEntregaRepository) {
        this.ordemEntregaRepository = ordemEntregaRepository;
        this.ordemEntregaMapper = ordemEntregaMapper;
        this.historicoOrdemEntregaRepository = historicoOrdemEntregaRepository;
    }


    public URI registrarOrdemDeEntrega(RequisicaoRegistrarOrdemEntregaDTO requicao) {
        AtomicReference<OrdemEntrega> atomicOrdemEntrega = new AtomicReference();
        OrdemEntrega ordemEntregaASerRegistrada =
                ordemEntregaMapper.toModel(requicao);
        ordemEntregaASerRegistrada.setStatusEntrega(StatusEntrega.PENDENTE);

        ordemEntregaASerRegistrada.setCodigoRastreamento(RastreamentoUtils.gerarCodigoDeRastreamento(requicao.getIdVenda()));

        OrdemEntrega ordemEntregaRegistrada =
                ordemEntregaRepository.saveAndFlush(ordemEntregaASerRegistrada);

        atomicOrdemEntrega.set(ordemEntregaRegistrada);

        HistoricoOrdemEntrega historicoASerRegistrado = HistoricoOrdemEntrega.builder()
                .ordemEntrega(ordemEntregaRegistrada)
                .statusAnterior(StatusEntrega.NOVO)
                .statusNovo(StatusEntrega.PENDENTE)
                .build();

        historicoOrdemEntregaRepository.saveAndFlush(historicoASerRegistrado);

        return ServletUriComponentsBuilder
                .fromPath("/v1/ordemServico")
                .path("/{id}")
                .build(atomicOrdemEntrega.get().getId());
    }

    public RespostaStatusEntregaDTO acompanharStatusEntrega(String codigoRastreamento) {
        OrdemEntrega ordemDeEntregaEncontrada = buscarOrdemEntregaPeloCodigoRastreamento(codigoRastreamento);
        return ordemEntregaMapper.toRespostaStatusEntregaDTO(ordemDeEntregaEncontrada);
    }

    @Transactional
    public RespostaStatusEntregaDTO atualizarStatusEntrega(RequisicaoAtualizarStatusEntregaDTO requisicao) {
        OrdemEntrega ordemDeEntregaAtualizada =
                atualizarStatusDaOrdemDeEntrega(requisicao);
                atualizarHistoricoDaOrdemDeEntrega(ordemDeEntregaAtualizada, requisicao);

        return ordemEntregaMapper.toRespostaStatusEntregaDTO(ordemDeEntregaAtualizada);
    }

    private void atualizarHistoricoDaOrdemDeEntrega(OrdemEntrega ordemDeEntregaAtualizada, RequisicaoAtualizarStatusEntregaDTO requisicao) {
        List<HistoricoOrdemEntrega> historico = ordemDeEntregaAtualizada.getHistorico();
        Collections.reverse(historico);

        if(historico.size() > 0) {
            HistoricoOrdemEntrega historicoOrdemEntregaASerRegistrado = HistoricoOrdemEntrega
                    .builder()
                    .ordemEntrega(ordemDeEntregaAtualizada)
                    .statusAnterior(historico.get(0).getStatusNovo())
                    .statusNovo(requisicao.getNovoStatus())
                .build();
            historicoOrdemEntregaRepository.saveAndFlush(historicoOrdemEntregaASerRegistrado);
        }
    }

    private OrdemEntrega atualizarStatusDaOrdemDeEntrega(RequisicaoAtualizarStatusEntregaDTO requisicao) {
        OrdemEntrega ordemDeEntregaEncontrada = buscarOrdemEntregaPeloIdVenda(requisicao.getIdVenda());
        ordemDeEntregaEncontrada.setStatusEntrega(requisicao.getNovoStatus());
        return ordemEntregaRepository.saveAndFlush(ordemDeEntregaEncontrada);
    }

    private OrdemEntrega buscarOrdemEntregaPeloIdVenda(Long idVenda) {
        return ordemEntregaRepository.findByIdVenda(idVenda)
                .orElseThrow(() -> {throw new OrdemDeEntregaNaoEncontradaPorIdVendaException(idVenda);});
    }

    private OrdemEntrega buscarOrdemEntregaPeloCodigoRastreamento(String codigoRastreamento) {
        return ordemEntregaRepository.findByCodigoRastreamento(codigoRastreamento)
                .orElseThrow(() -> {throw new OrdemDeEntregaNaoEncontradaPorCodigoRastreamentoException(codigoRastreamento);});
    }

}
