package br.com.lecom.transportadora.transportadora.exception;

import javax.persistence.EntityNotFoundException;

public class OrdemDeEntregaNaoEncontradaPorCodigoRastreamentoException extends EntityNotFoundException {
    public OrdemDeEntregaNaoEncontradaPorCodigoRastreamentoException(String codigoRastreamento) {
        super(String.format("Ordem de entrega com o código de rastreamento %s não encontrada", codigoRastreamento));
    }
}
