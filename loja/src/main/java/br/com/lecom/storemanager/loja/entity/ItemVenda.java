package br.com.lecom.storemanager.loja.entity;

import br.com.lecom.storemanager.entity.Auditable;
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
@Entity(name = "item_venda")
public class ItemVenda extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @Column(name = "produto_id", nullable = false)
    private Long idProduto;

    @Digits(integer=6, fraction=2)
    @DecimalMin("1.00")
    @Column(name = "PRECO_UNITARIO")
    private BigDecimal precoUnitario;

    private Integer quantidade;
}