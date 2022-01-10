package br.com.lecom.storemanager.loja.mapper;

import br.com.lecom.storemanager.loja.dto.RequisicaoCadastrarLojaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaBuscarLojaDTO;
import br.com.lecom.storemanager.loja.entity.Loja;
import org.mapstruct.Mapper;

@Mapper
public interface LojaMapper {
    Loja toModel(RequisicaoCadastrarLojaDTO requisicao);
    RespostaBuscarLojaDTO toDTO(Loja loja);
}