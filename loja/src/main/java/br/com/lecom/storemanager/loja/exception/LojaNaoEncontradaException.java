package br.com.lecom.storemanager.loja.exception;

import javax.persistence.EntityNotFoundException;

public class LojaNaoEncontradaException extends EntityNotFoundException {
    public LojaNaoEncontradaException(Long id) {
        super(String.format("Loja com o id %s não encontrada!", id));
    }
}
