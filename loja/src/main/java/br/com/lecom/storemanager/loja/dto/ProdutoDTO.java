package br.com.lecom.storemanager.loja.dto;

import br.com.lecom.storemanager.loja.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoDTO implements Serializable {

    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private Categoria categoria;

    @NotNull
    @NotEmpty
    @JsonProperty("preco-unitario")
    private BigDecimal precoUnitario;
}