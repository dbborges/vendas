package br.com.lecom.catalogomanager.catalogo.service;

import br.com.lecom.catalogomanager.catalogo.dto.ProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RequisicaoCadastrarProdutoDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RespostaBuscarLojaDTO;
import br.com.lecom.catalogomanager.catalogo.dto.RespostaBuscarTodosProdutosDTO;
import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.exception.LojaNaoEncontradaException;
import br.com.lecom.catalogomanager.catalogo.exception.ProdutoJaExisteException;
import br.com.lecom.catalogomanager.catalogo.exception.ProdutoNaoEncontradoException;
import br.com.lecom.catalogomanager.catalogo.mapper.ProdutoMapper;
import br.com.lecom.catalogomanager.catalogo.repository.ProdutoRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
@Log4j2
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final String uriLoja;

    public ProdutoService(ProdutoRepository produtoRepository,
                          ProdutoMapper produtoMapper,
                          @Value("http://localhost:8080/loja-api/v1/lojas") String uriLoja
    ) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
        this.uriLoja = uriLoja;
    }

    public URI cadastrar(RequisicaoCadastrarProdutoDTO requisicao) {
        log.info("Cadastrando o produto {} na loja com id {}", requisicao, requisicao.getIdLoja());

        Boolean isLojaEncontrada =
                verificaSeLojaExistePeloId(requisicao.getIdLoja());
        if(!isLojaEncontrada) {
            throw new LojaNaoEncontradaException(requisicao.getIdLoja());
        }
        validaSeProdutoExistePeloNome(requisicao.getNome(), requisicao.getIdLoja());

        Produto produtoParaCadastrar = produtoMapper.toModel(requisicao);
        Produto produtoCadastrado = produtoRepository.saveAndFlush(produtoParaCadastrar);

        return ServletUriComponentsBuilder
                .fromPath("/v1/produtos")
                .path("/{id}")
                .build(requisicao.getId());
    }

    private void validaSeProdutoExistePeloNome(String nomeProduto, Long idLoja) {
        produtoRepository.findProdutoByNomeAndIdLoja(nomeProduto, idLoja)
                .ifPresent(produto -> {throw new ProdutoJaExisteException(nomeProduto, idLoja);});
    }

    private Boolean verificaSeLojaExistePeloId(Long idLoja) {
        RestTemplate restTemplate = new RestTemplate();
        String requisicao = uriLoja
                .concat("/")
                .concat(String.valueOf(idLoja))
                .concat("/existe");
        ParameterizedTypeReference<Boolean> responseType = new ParameterizedTypeReference<>() { };
        ResponseEntity<Boolean> result = restTemplate
                .exchange(requisicao, HttpMethod.GET, null, responseType);
        if (result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        }
        return null;
    }

    public Page<RespostaBuscarTodosProdutosDTO> buscarTodos(Pageable pageAble) {
        final var pagina = produtoRepository.findAll(pageAble);
        if (pagina.hasContent()) {
            final var listaDeProdutosEncontrados = pagina.getContent()
                    .stream().map(RespostaBuscarTodosProdutosDTO::new)
                    .collect(Collectors.toList());
            return new PageImpl<>(
                    listaDeProdutosEncontrados,
                    pagina.getPageable(),
                    pagina.getTotalElements()
            );
        }
        return Page.empty();
    }

    public Page<RespostaBuscarTodosProdutosDTO> buscarTodos(
            final String search,
            final Integer page,
            final Integer size,
            final String sort,
            final String direction
    ) {
        Optional<Pageable> pageable = PageRequest.of(page, size, Sort.Direction.valueOf(direction), sort).toOptional();
        if (StringUtils.isNotEmpty(search)) {
            Page<Produto> pagina = produtoRepository.buscarUsandoFiltros(search, pageable.get());
            if (pagina.hasContent()) {
                final var listaDeProdutos = pagina.getContent()
                        .stream().map(RespostaBuscarTodosProdutosDTO::new)
                        .collect(Collectors.toList());
                return new PageImpl<>(
                        listaDeProdutos,
                        pagina.getPageable(),
                        pagina.getTotalElements()
                );
            }
        } else {
            return buscarTodos(pageable.get());
        }

        return Page.empty();
    }

    public ProdutoDTO buscarPorId(Long id) {
        var produtoEncontrado = produtoRepository.findById(id);
        if (produtoEncontrado.isEmpty()) {
            throw new ProdutoNaoEncontradoException(id);
        }
        return new ProdutoDTO(produtoEncontrado.get());
    }
}
