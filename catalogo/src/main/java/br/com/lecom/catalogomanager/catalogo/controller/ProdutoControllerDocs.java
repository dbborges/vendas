package br.com.lecom.catalogomanager.catalogo.controller;

import br.com.lecom.catalogomanager.catalogo.dto.ProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RequisicaoCadastrarProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RespostaBuscarTodosProdutosDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;

@Api("Gerenciamento de Produtos")
public interface ProdutoControllerDocs {

    @ApiOperation(value = "Busca todos os produtos cadastrados no sistema usando filtros com ordenação e paginação", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de produtos com paginação", response = Page.class),
            @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    Page<RespostaBuscarTodosProdutosDTO> buscarTodos(String search, Integer page, Integer size, String sort, String direction);

    @ApiOperation(value = "Busca um produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Produto não encontrado")
    })
    ResponseEntity<ProdutoDTO> buscarPeloId(Long id);

    @ApiOperation(value = "Cadastra um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível cadastrar o produto")
    })
    ResponseEntity<URI> cadastrar(RequisicaoCadastrarProdutoDTO requisicao);
}