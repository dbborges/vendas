package br.com.lecom.transportadora.transportadora.mapper;

import br.com.lecom.transportadora.transportadora.dto.RequisicaoRegistrarOrdemEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RespostaAtualizarStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RespostaRegistrarOrdemEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RespostaStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.entity.OrdemEntrega;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrdemEntregaMapper {
    OrdemEntrega toModel(RequisicaoRegistrarOrdemEntregaDTO requicao);
    RespostaRegistrarOrdemEntregaDTO toDTO(OrdemEntrega requisicao);
    RespostaStatusEntregaDTO toRespostaStatusEntregaDTO(OrdemEntrega ordemEntrega);
    RespostaAtualizarStatusEntregaDTO toRespostaAtualizarStatusEntregaDTO(OrdemEntrega ordemEntrega);

}