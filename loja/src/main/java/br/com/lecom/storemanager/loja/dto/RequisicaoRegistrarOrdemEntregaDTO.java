package br.com.lecom.storemanager.loja.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoRegistrarOrdemEntregaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1344498314471912971L;

    @JsonProperty("id-venda")
    private Long idVenda;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;
}