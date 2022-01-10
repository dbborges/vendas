package br.com.lecom.catalogomanager.catalogo.exception;

import javax.persistence.EntityNotFoundException;

public class ProdutoNaoEncontradoException extends EntityNotFoundException {
    public ProdutoNaoEncontradoException(Long id) {
        super(String.format("Produto com o id %s não encontrado!", id));
    }
}
