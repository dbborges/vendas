package br.com.lecom.storemanager.loja.mapper;

import br.com.lecom.storemanager.loja.dto.ItemVendaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaBuscarVendasDTO;
import br.com.lecom.storemanager.loja.dto.RespostaRegistrarVendasDTO;
import br.com.lecom.storemanager.loja.entity.ItemVenda;
import br.com.lecom.storemanager.loja.entity.Venda;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-03-11T19:21:44+0100",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 13.0.2 (Oracle Corporation)"
)
@Component
public class vendaMapperImpl implements VendaMapper{
    @Override
    public RespostaBuscarVendasDTO toRespostaBuscarVendasDTO(Venda venda) {
        if(venda == null) {
            return null;
        }

        List<ItemVendaDTO> itens = new ArrayList<>();
        for (ItemVenda item : venda.getItensVenda()) {
            itens.add(ItemVendaDTO.builder()
                    .id(item.getId())
                    .idProduto(item.getIdProduto())
                    .precoUnitario(item.getPrecoUnitario())
                    .quantidade(item.getQuantidade())
                    .build());
        }

        return RespostaBuscarVendasDTO.builder()
                .id(venda.getId())
                .dataDaCompra(venda.getDataDaCompra())
                .idLoja(venda.getLoja().getId())
                .itensVenda(itens)
                .build();
    }

    @Override
    public RespostaRegistrarVendasDTO toRespostaRegistrarVendasDTO(Venda venda) {
        return null;
    }
}
