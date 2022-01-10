package br.com.lecom.catalogomanager.catalogo.mapper;

import br.com.lecom.catalogomanager.catalogo.dto.ProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RequisicaoCadastrarProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import org.mapstruct.Mapper;

@Mapper
public interface ProdutoMapper {

    Produto toModel(RequisicaoCadastrarProdutoDTO requisicao);

    ProdutoDTO toDTO(Produto produto);
}