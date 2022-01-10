package br.com.lecom.storemanager.loja.dto;

import br.com.lecom.storemanager.loja.enums.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoAtualizarStatusEntregaDTO implements Serializable {

    @NotNull
    @JsonProperty("venda-id")
    private Long idVenda;

    @NotNull
    @JsonProperty("status")
    private StatusEntrega status;

}
