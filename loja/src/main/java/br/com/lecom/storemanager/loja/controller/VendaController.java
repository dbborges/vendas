package br.com.lecom.storemanager.loja.controller;

import br.com.lecom.storemanager.loja.dto.*;
import br.com.lecom.storemanager.loja.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/vendas")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrarVenda(@RequestBody RequisicaoRegistrarVendasDTO requisicao) {
        return ResponseEntity.ok(vendaService.registrarVenda(requisicao));
    }

    @PostMapping(path = "/buscar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RespostaBuscarVendasDTO>> buscarVendas(
            @RequestBody RequisicaoBuscarVendasDTO requisicao) {
        return ResponseEntity.ok(vendaService.buscarVendas(requisicao));
    }

    @PostMapping("/atualizarStatusEntrega")
    public ResponseEntity<RespostaAtualizarStatusEntregaDTO> atualizarStatusEntrega(
            @Valid @RequestBody RequisicaoAtualizarStatusEntregaDTO requisicao) {
        return ResponseEntity.ok(vendaService.atualizarStatusEntrega(requisicao));
    }
}
