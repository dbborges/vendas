package br.com.lecom.transportadora.transportadora.controller;

import br.com.lecom.transportadora.transportadora.dto.RequisicaoAtualizarStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RequisicaoRegistrarOrdemEntregaDTO;
import br.com.lecom.transportadora.transportadora.dto.RespostaStatusEntregaDTO;
import br.com.lecom.transportadora.transportadora.service.OrdemEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/ordensEntrega")
public class OrdemEntregaController {

    private final OrdemEntregaService ordemEntregaService;

    public OrdemEntregaController(OrdemEntregaService ordemEntregaService) {
        this.ordemEntregaService = ordemEntregaService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity registrarOrdemDeEntrega(
            @Valid @RequestBody RequisicaoRegistrarOrdemEntregaDTO requisicao) {

        URI uri = ordemEntregaService.registrarOrdemDeEntrega(requisicao);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/rastreamento/{codigoRastreamento}")
    public ResponseEntity<RespostaStatusEntregaDTO> acompanharStatusEntrega(@PathVariable String codigoRastreamento) {
        return ResponseEntity.ok(ordemEntregaService.acompanharStatusEntrega(codigoRastreamento));
    }

    @PostMapping(path = "/atualizarStatusEntrega", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarStatusEntrega(
            @RequestBody RequisicaoAtualizarStatusEntregaDTO requisicao) {
        return ResponseEntity.ok(ordemEntregaService.atualizarStatusEntrega(requisicao));
    }
}
