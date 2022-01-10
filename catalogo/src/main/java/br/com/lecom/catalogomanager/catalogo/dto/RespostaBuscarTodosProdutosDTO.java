package br.com.lecom.catalogomanager.catalogo.dto;

import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaBuscarTodosProdutosDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 595697938569403976L;

    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private Categoria categoria;

    @JsonProperty("preco-unitario")
    private BigDecimal precoUnitario;

    @JsonProperty("loja_id")
    private Long idLoja;

    public RespostaBuscarTodosProdutosDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.categoria = produto.getCategoria();
        this.precoUnitario = produto.getPrecoUnitario();
        this.idLoja = produto.getIdLoja();

    }
}