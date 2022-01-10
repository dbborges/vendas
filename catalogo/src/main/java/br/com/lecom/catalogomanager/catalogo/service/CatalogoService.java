package br.com.lecom.catalogomanager.catalogo.service;

import br.com.lecom.catalogomanager.catalogo.dto.CatalogoDTO;
import br.com.lecom.catalogomanager.catalogo.entity.Catalogo;
import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import br.com.lecom.catalogomanager.catalogo.exception.CatalogoJaExisteException;
import br.com.lecom.catalogomanager.catalogo.exception.CatalogoNaoEncontradoException;
import br.com.lecom.catalogomanager.catalogo.mapper.CatalogoMapper;
import br.com.lecom.catalogomanager.catalogo.mapper.ProdutoMapper;
import br.com.lecom.catalogomanager.catalogo.repository.CatalogoRepository;
import com.google.common.base.Preconditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

//@Service
public class CatalogoService {
//    private final CatalogoRepository catalogoRepository;
//    private final ProdutoService produtoService;
//    private final CatalogoMapper catalogoMapper;
//    private final ProdutoMapper produtoMapper;
//
//    public CatalogoService(CatalogoRepository catalogoRepository, ProdutoService produtoService, CatalogoMapper catalogoMapper, ProdutoMapper produtoMapper) {
//        this.catalogoRepository = catalogoRepository;
//        this.produtoService = produtoService;
//        this.catalogoMapper = catalogoMapper;
//        this.produtoMapper = produtoMapper;
//    }
//
//    public Page<CatalogoDTO> buscarTodos(Pageable pageAble) {
//        final var page = catalogoRepository.findAll(pageAble);
//        if (page.hasContent()) {
//            final var catalogDTOList = page.getContent()
//                    .stream().map(CatalogoDTO::new)
//                    .collect(Collectors.toList());
//            return new PageImpl<>(
//                    catalogDTOList,
//                    page.getPageable(),
//                    page.getTotalElements()
//            );
//        }
//        return Page.empty();
//    }
//
//    public CatalogoDTO cadastrar(CatalogoDTO catalogoDTO) {
//        verificarSeExiste(catalogoDTO);
//        final Catalogo catalogoParaCadastrar = catalogoMapper.toModel(catalogoDTO);
//        cadastrarProdutos(catalogoParaCadastrar.getIdLoja(), catalogoParaCadastrar.getProdutos());
//        final Catalogo catalogoCadastrado = catalogoRepository.saveAndFlush(catalogoParaCadastrar);
//        return catalogoMapper.toDTO(catalogoCadastrado);
//    }
//
//    private void cadastrarProdutos(Long idLoja, Set<Produto> produtos) {
//        produtos.forEach(p -> produtoService.cadastrar(idLoja, produtoMapper.toDTO(p)));
//    }
//
//    private void verificarSeExiste(CatalogoDTO catalogoDTO) {
//        final String cnpjInformado = catalogoDTO.getCnpj();
//        catalogoRepository.findByCnpj(cnpjInformado)
//                .ifPresent(cat -> { throw new CatalogoJaExisteException(cnpjInformado); });
//    }
//
//    public CatalogoDTO patch(
//            Long catalogId,
//            CatalogoDTO catalogoDTO
//    ) {
//        Preconditions.checkArgument(catalogId != null, "Id não pode ser nulo", catalogId);
//        return catalogoDTO;
//    }
//
//    public CatalogoDTO buscarPorId(Long idCatalogo) {
//        var catalogoEncontrado = catalogoRepository.findById(idCatalogo);
//        if (catalogoEncontrado.isEmpty()) {
//            throw new CatalogoNaoEncontradoException(idCatalogo);
//        }
//        return  catalogoMapper.toDTO(catalogoEncontrado.get());
//    }
}
