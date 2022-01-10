package br.com.lecom.catalogomanager.catalogo.exception;

import javax.persistence.EntityNotFoundException;

public class CatalogoJaExisteException extends EntityNotFoundException {
    public CatalogoJaExisteException(String cnpj) {
        super(String.format("Catalogo com o cnpj %s já existe!", cnpj));
    }
}