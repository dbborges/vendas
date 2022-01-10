package br.com.lecom.storemanager.loja.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaRegistrarOrdemEntregaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3503225112045135202L;

    private Long id;

    @JsonProperty("id-venda")
    private Long idVenda;

    @JsonProperty("data-de-registro")
    private LocalDateTime dataDeRegistro;

    @JsonProperty("codigo-rastreamento")
    private String codigoRastreamento;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;
}