package br.com.lecom.catalogomanager.catalogo.controller;

import br.com.lecom.catalogomanager.catalogo.dto.ProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RequisicaoCadastrarProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RespostaBuscarTodosProdutosDTO;
import br.com.lecom.catalogomanager.catalogo.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController implements ProdutoControllerDocs {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public Page<RespostaBuscarTodosProdutosDTO> buscarTodos(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        return produtoService.buscarTodos(search, page, size, sort, direction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPeloId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping()
    public ResponseEntity<URI> cadastrar(
            @Valid @RequestBody RequisicaoCadastrarProdutoDTO requisicao
    ) {
        URI uri = produtoService.cadastrar(requisicao);
        return ResponseEntity.created(uri).build();
    }
}