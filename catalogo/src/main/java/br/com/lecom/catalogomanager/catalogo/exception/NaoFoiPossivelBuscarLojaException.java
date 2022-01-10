package br.com.lecom.catalogomanager.catalogo.exception;

import javax.persistence.EntityNotFoundException;

public class NaoFoiPossivelBuscarLojaException extends Exception {
    public NaoFoiPossivelBuscarLojaException(Long id) {
        super(String.format("Não foi possível buscar a loja com o id %s!", id));
    }
}
