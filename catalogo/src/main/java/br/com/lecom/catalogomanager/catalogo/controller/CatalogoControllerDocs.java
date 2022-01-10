package br.com.lecom.catalogomanager.catalogo.controller;

import br.com.lecom.catalogomanager.catalogo.dto.CatalogoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api("Gerenciamento de Catalogos")
public interface CatalogoControllerDocs {

    @ApiOperation(value = "Busca todos os catalogos cadastrados no sistema com paginação", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de catalogos com paginação", response = Page.class),
            @ApiResponse(code = 404, message = "Catalogo não encontrado")
    })
    Page<CatalogoDTO> buscarTodos(Pageable pageable);

    @ApiOperation(value = "Busca um catalogo pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Catalogo encontrado com sucesso"),
            @ApiResponse(code = 404, message = "Catalogo não encontrado")
    })
    ResponseEntity<CatalogoDTO> buscarPeloId(Long idCatalogo);

    @ApiOperation(value = "Cadastra um catalogo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Catalogo cadastrado com sucesso"),
            @ApiResponse(code = 400, message = "Não foi possível cadastrar o catalogo")
    })
    ResponseEntity<CatalogoDTO> cadastrar(CatalogoDTO catalogoDTO);
}