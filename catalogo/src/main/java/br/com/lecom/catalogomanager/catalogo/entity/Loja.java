package br.com.lecom.catalogomanager.catalogo.entity;

import br.com.lecom.catalogomanager.entity.Auditable;
import br.com.lecom.catalogomanager.enums.TipoLogradouro;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Loja extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String cnpj;

    @Column(name = "razao_social", nullable = false)
    private String razaoSocial;

    @Column(name = "nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String cep;

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
}