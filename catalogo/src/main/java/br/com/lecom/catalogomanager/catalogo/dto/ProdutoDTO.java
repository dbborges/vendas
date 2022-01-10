package br.com.lecom.catalogomanager.catalogo.dto;

import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
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

//    @NotEmpty
    private String nome;

//    @NotEmpty
    private Categoria categoria;

//    @NotNull
    @JsonProperty("preco-unitario")
    private BigDecimal precoUnitario;

    @JsonProperty("loja_id")
    private Long idLoja;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.precoUnitario = produto.getPrecoUnitario();
        this.idLoja = produto.getIdLoja();

    }
}