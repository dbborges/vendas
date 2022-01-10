package br.com.lecom.transportadora.transportadora.entity;

import br.com.lecom.transportadora.entity.Auditable;
import br.com.lecom.transportadora.transportadora.enums.StatusEntrega;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity(name = "HISTORICO_ORDEM_ENTREGA")
public class HistoricoOrdemEntrega extends Auditable implements Comparable<HistoricoOrdemEntrega> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordem_entrega_id")
    private OrdemEntrega ordemEntrega;

    @CreatedDate
    @Column(name = "DATA_MOVIMENTACAO")
    private LocalDateTime dataDaMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_ANTERIOR")
    private StatusEntrega statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_NOVO")
    private StatusEntrega statusNovo;

    @Override
    public int compareTo(HistoricoOrdemEntrega o) {
        if(getId() == null || o.getId() == null) {
            return 0;
        }
        return getId().compareTo(o.getId());
    }
}