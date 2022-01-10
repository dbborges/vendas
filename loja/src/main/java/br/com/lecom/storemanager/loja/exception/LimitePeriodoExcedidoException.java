package br.com.lecom.storemanager.loja.exception;

public class LimitePeriodoExcedidoException extends RuntimeException {
    public LimitePeriodoExcedidoException(Long dias) {
        super(String.format("Limite de período de busca excedido! O limite é de %s dias", dias));
    }
}
