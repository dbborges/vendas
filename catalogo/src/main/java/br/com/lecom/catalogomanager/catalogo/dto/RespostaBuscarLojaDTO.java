package br.com.lecom.catalogomanager.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaBuscarLojaDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -4465069926630622040L;

    private Long id;

    @CNPJ
    private String cnpj;

    @NotEmpty
    @JsonProperty("razao-social")
    private String razaoSocial;

    @NotEmpty
    @JsonProperty("nome-fantasia")
    private String nomeFantasia;

    @NotEmpty
    private String cep;

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

}