package br.com.lecom.storemanager.loja.entity;

import br.com.lecom.storemanager.entity.Auditable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Venda extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private Loja loja;

    @Column(name = "data_compra")
    private LocalDateTime dataDaCompra;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "venda",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    private List<ItemVenda> itensVenda;
}
