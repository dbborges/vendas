package br.com.lecom.transportadora.transportadora.entity;

import br.com.lecom.transportadora.entity.Auditable;
import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import br.com.lecom.transportadora.transportadora.enums.TipoLogradouro;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "ORDEM_ENTREGA")
public class OrdemEntrega extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "VENDA_ID")
    private Long idVenda;

    @CreatedDate
    private LocalDateTime dataDeRegistro;

    @Column(name = "CODIGO_RASTREAMENTO")
    private String codigoRastreamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusEntrega statusEntrega;

    @Column(nullable = false)
    private String cep;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_logradouro")
    private TipoLogradouro tipoDeLogradouro;

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
            mappedBy = "ordemEntrega",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
    )
    private List<HistoricoOrdemEntrega> historico;

}