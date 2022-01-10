package br.com.lecom.catalogomanager.catalogo.dto;

import br.com.lecom.catalogomanager.catalogo.entity.Loja;
import br.com.lecom.catalogomanager.enums.TipoLogradouro;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class LojaDTO implements Serializable {

    private Long id;

    @CNPJ
    @NotNull
    @NotEmpty
    private String cnpj;

    @NotNull
    @NotEmpty
    @JsonProperty("razao-social")
    private String razaoSocial;

    @NotNull
    @NotEmpty
    @JsonProperty("nome-fantasia")
    private String nomeFantasia;

    @NotNull
    @NotEmpty
    private String cep;

    @NotNull
    @NotEmpty
    @JsonProperty("tipo-logradouro")
    private TipoLogradouro tipoDeLogradouro;

    @NotNull
    @NotEmpty
    private String logradouro;

    @NotNull
    @NotEmpty
    private String numero;

    private String complemento;

    @NotNull
    @NotEmpty
    private String bairro;

    @NotNull
    @NotEmpty
    private String cidade;

    @NotNull
    @NotEmpty
    private String uf;

    public LojaDTO(Loja loja) {
        this.id = loja.getId();
        this.cnpj = loja.getCnpj();
        this.razaoSocial = loja.getRazaoSocial();
        this.nomeFantasia = loja.getNomeFantasia();
        this.cep = loja.getCep();
        this.tipoDeLogradouro = loja.getTipoDeLogradouro();
        this.logradouro = loja.getLogradouro();
        this.numero = loja.getNumero();
        this.complemento = loja.getComplemento();
        this.bairro = loja.getBairro();
        this.cidade = loja.getCidade();
        this.uf = loja.getUf();
    }
}