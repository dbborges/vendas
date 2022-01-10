package br.com.lecom.transportadora.transportadora.dto;

import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoAtualizarStatusEntregaDTO {

    @JsonProperty("venda-id")
    private Long idVenda;

    @JsonProperty("status")
    private StatusEntrega novoStatus;

}
