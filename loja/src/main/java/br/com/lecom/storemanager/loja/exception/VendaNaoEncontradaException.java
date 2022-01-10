package br.com.lecom.storemanager.loja.exception;

import javax.persistence.EntityNotFoundException;

public class VendaNaoEncontradaException extends EntityNotFoundException {
    public VendaNaoEncontradaException(Long idVenda) {
        super(String.format("Venda com o id %s não encontrada!", idVenda));
    }
}
