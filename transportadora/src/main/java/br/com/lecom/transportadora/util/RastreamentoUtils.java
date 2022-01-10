package br.com.lecom.transportadora.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class RastreamentoUtils {
    public static String gerarCodigoDeRastreamento(Long idVenda) {
        return String.valueOf(idVenda)
                .concat("-")
                .concat(String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
    }
}