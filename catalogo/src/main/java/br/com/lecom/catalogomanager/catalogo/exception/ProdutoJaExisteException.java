package br.com.lecom.catalogomanager.catalogo.exception;

import javax.persistence.EntityExistsException;

public class ProdutoJaExisteException extends EntityExistsException {
    public ProdutoJaExisteException(String nome, Long idLoja) {
        super(String.format("Produto com o nome %s já existe na loja %s!", nome, idLoja));
    }
}