package br.com.lecom.storemanager.loja.mapper;

import br.com.lecom.storemanager.loja.dto.RespostaBuscarVendasDTO;
import br.com.lecom.storemanager.loja.dto.RespostaRegistrarVendasDTO;
import br.com.lecom.storemanager.loja.entity.Venda;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface VendaMapper {


    RespostaBuscarVendasDTO toRespostaBuscarVendasDTO(Venda venda);

    RespostaRegistrarVendasDTO toRespostaRegistrarVendasDTO(Venda venda);
}