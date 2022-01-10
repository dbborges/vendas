package br.com.lecom.storemanager.loja.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemVendaDTO {

    private Long id;

    @JsonProperty("produto_id")
    private Long idProduto;

    @JsonProperty("preco-unitario")
    private BigDecimal precoUnitario;

    private Integer quantidade;
}
