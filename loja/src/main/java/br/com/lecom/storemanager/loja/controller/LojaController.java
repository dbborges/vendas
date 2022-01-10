package br.com.lecom.storemanager.loja.controller;

import br.com.lecom.storemanager.loja.dto.ProdutoDTO;
import br.com.lecom.storemanager.loja.dto.RequisicaoCadastrarLojaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaBuscarLojaDTO;
import br.com.lecom.storemanager.loja.dto.RespostaCadastrarLojaDTO;
import br.com.lecom.storemanager.loja.service.LojaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/lojas")
public class LojaController{

    private final LojaService lojaService;

    public LojaController(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<URI> cadastrar(
            @Valid @RequestBody RequisicaoCadastrarLojaDTO requisicao) {
        URI uri = lojaService.cadastrar(requisicao);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaBuscarLojaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(lojaService.buscarPorId(id));
    }

    @GetMapping
    public Page<RespostaCadastrarLojaDTO> buscarTodos(Pageable pageable) {
        return lojaService.buscarTodos(pageable);
    }

    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> verificaSeExiste(@PathVariable Long id) {
        return ResponseEntity.ok(lojaService.verificaSeExiste(id));
    }

    @GetMapping("/{id}/catalogo/itens")
    public ResponseEntity<Page<ProdutoDTO>> buscarItensNaLoja(
            @PathVariable Long id,
            Pageable pageable
    ) {
        return ResponseEntity.ok(lojaService.buscarItensNaLoja(id, pageable));
    }
}