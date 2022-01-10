package br.com.lecom.catalogomanager.catalogo.entity;

import br.com.lecom.catalogomanager.catalogo.dto.ProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
import br.com.lecom.catalogomanager.entity.Auditable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria")
    private Categoria categoria;

    @Digits(integer=6, fraction=2)
    @DecimalMin("1.00")
    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

    @Column(name = "loja_id", nullable = false)
    private Long idLoja;

    public Produto(ProdutoDTO produtoDTO, Long idLoja) {
        this.nome = produtoDTO.getNome();
        this.categoria = produtoDTO.getCategoria();
        this.precoUnitario = produtoDTO.getPrecoUnitario();
        this.idLoja = idLoja;
    }
}