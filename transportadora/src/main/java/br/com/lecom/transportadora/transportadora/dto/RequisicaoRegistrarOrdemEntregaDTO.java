package br.com.lecom.transportadora.transportadora.dto;

import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import br.com.lecom.transportadora.transportadora.enums.TipoLogradouro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoRegistrarOrdemEntregaDTO implements Serializable {

    @JsonProperty("id-venda")
    private Long idVenda;

    private String cep;

    @JsonProperty("tipo-logradouro")
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