package br.com.lecom.catalogomanager.catalogo.exception;

import org.springframework.dao.EmptyResultDataAccessException;

public class CatalogoNaoEncontradoException extends EmptyResultDataAccessException {
    public CatalogoNaoEncontradoException(Long id) {
        super(String.format("Catalogo com o id %s não encontrado!", id), 1);
    }
}