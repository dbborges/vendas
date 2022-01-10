package br.com.lecom.transportadora.transportadora.exception;

import javax.persistence.EntityNotFoundException;

public class OrdemDeEntregaNaoEncontradaPorIdVendaException extends EntityNotFoundException {
    public OrdemDeEntregaNaoEncontradaPorIdVendaException(Long idVenda) {
        super(String.format("Ordem de entrega com o id da venda %s não encontrada", idVenda));
    }
}
