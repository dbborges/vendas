package br.com.lecom.catalogomanager.catalogo.dto;

import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RequisicaoCadastrarProdutoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -310328905707841749L;

    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty
    private String nome;

    @NotNull
    private Categoria categoria;

    @NotNull
    @JsonProperty("preco-unitario")
    private BigDecimal precoUnitario;

    @NotNull
    @JsonProperty("loja_id")
    private Long idLoja;

    public RequisicaoCadastrarProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.precoUnitario = produto.getPrecoUnitario();
        this.idLoja = produto.getIdLoja();

    }
}