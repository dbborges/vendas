package br.com.lecom.transportadora.transportadora.dto;

import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import br.com.lecom.transportadora.transportadora.enums.TipoLogradouro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaStatusEntregaDTO {

    @JsonProperty("venda-id")
    private Long idVenda;

    @JsonProperty("codigo-rastreamento")
    private String codigoRastreamento;

    @JsonProperty("status")
    private StatusEntrega statusEntrega;

    private String cep;

    @JsonProperty("tipo-de-logradouro")
    private TipoLogradouro tipoDeLogradouro;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

}