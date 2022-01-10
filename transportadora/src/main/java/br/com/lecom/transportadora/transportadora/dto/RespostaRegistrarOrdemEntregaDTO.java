package br.com.lecom.transportadora.transportadora.dto;

import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import br.com.lecom.transportadora.transportadora.enums.TipoLogradouro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaRegistrarOrdemEntregaDTO {

    private Long id;

    @JsonProperty("id-venda")
    private Long idVenda;

    @JsonProperty("data-de-registro")
    private LocalDateTime dataDeRegistro;

    @JsonProperty("codigo-rastreamento")
    private String codigoRastreamento;

    private String cep;

    @JsonProperty("tipo-de-logradouro")
    private TipoLogradouro tipoDeLogradouro;

    @JsonProperty("status")
    private StatusEntrega statusEntrega;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;
}