package br.com.lecom.catalogomanager.catalogo.dto;

import br.com.lecom.catalogomanager.catalogo.entity.Catalogo;
import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatalogoDTO implements Serializable {

//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @JsonProperty("id-loja")
//    private Long idLoja;
//
//    @NotNull
//    @NotEmpty
//    private String nome;
//
//    @NotNull
//    @NotEmpty
//    private String cnpj;
//
//    @NotNull
//    private Set<ProdutoDTO> produtos;
//
//    public CatalogoDTO(Produto produto) {
//        this.id = produto.getId();
//        this.nome = produto.getNome();
//    }
//
//    public CatalogoDTO(Catalogo catalogo) {
//        this.id = catalogo.getId();
//        this.nome = catalogo.getNome();
//        this.cnpj = catalogo.getCnpj();
//        this.produtos = catalogo.getProdutos()
//                .stream().map(ProdutoDTO::new)
//                .collect(Collectors.toSet());
//    }
}