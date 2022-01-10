package br.com.lecom.storemanager.loja.exception;

import javax.persistence.EntityExistsException;

public class LojaJaExisteException extends EntityExistsException {
    public LojaJaExisteException(String razaoSocial) {
        super(String.format("Loja com a razão social %s já existe!", razaoSocial));
    }
}