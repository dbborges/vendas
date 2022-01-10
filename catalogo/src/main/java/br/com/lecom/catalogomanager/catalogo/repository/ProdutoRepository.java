package br.com.lecom.catalogomanager.catalogo.repository;

import br.com.lecom.catalogomanager.catalogo.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findProdutoByNomeAndIdLoja(String nome, Long idLoja);

    @Query("FROM Produto p where p.nome = :search")
    Page<Produto> buscarUsandoFiltros(
            @Param("search") String search,
            Pageable pageable
    );
}
