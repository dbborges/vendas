package br.com.lecom.catalogomanager.catalogo.entity;

import br.com.lecom.catalogomanager.catalogo.dto.CatalogoDTO;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
import br.com.lecom.catalogomanager.entity.Auditable;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@Entity
//@EntityListeners(AuditingEntityListener.class)
public class Catalogo extends Auditable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Column(name = "loja_id")
//    private Long idLoja;
//
//    private String nome;
//
//    @EqualsAndHashCode.Include
//    @Column(length = 14)
//    private String cnpj;
//
//    @OneToMany(
//            fetch = FetchType.LAZY,
//            mappedBy = "catalogo",
//            cascade = { CascadeType.PERSIST, CascadeType.MERGE }
//    )
//    private Set<Produto> produtos;
//
//    public Catalogo(CatalogoDTO catalogoDTO) {
//        this.id = catalogoDTO.getId() != null ? catalogoDTO.getId() : null;
//        this.idLoja = catalogoDTO.getIdLoja();
//        this.nome = catalogoDTO.getNome();
//        this.cnpj = catalogoDTO.getCnpj();
//        this.produtos = catalogoDTO.getProdutos()
//                .stream().map(p -> new Produto(p, this)).collect(Collectors.toSet());
//    }

}