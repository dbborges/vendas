package br.com.lecom.storemanager.loja.mapper;

import br.com.lecom.storemanager.loja.dto.ItemVendaDTO;
import br.com.lecom.storemanager.loja.entity.ItemVenda;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ItemVendaMapper {

    ItemVenda toModel(ItemVendaDTO itemVendaDTO);

    ItemVendaDTO toDTO(ItemVenda itemVenda);
}