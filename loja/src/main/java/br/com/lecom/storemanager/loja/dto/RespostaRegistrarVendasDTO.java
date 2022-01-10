package br.com.lecom.storemanager.loja.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespostaRegistrarVendasDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6089272105469255263L;

    private Long id;

    @JsonProperty("loja-id")
    private Long idLoja;

    @JsonProperty("data-da-compra")
    private LocalDate dataDaCompra;

    private String cep;

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    @JsonProperty("itens-venda")
    private List<ItemVendaDTO> itensVenda = new ArrayList<>();

}