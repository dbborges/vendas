package br.com.lecom.storemanager.loja.repository;

import br.com.lecom.storemanager.loja.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM Venda v JOIN FETCH v.loja l WHERE l.id =:idLoja AND v.dataDaCompra BETWEEN :start and :end")
    List<Venda> findByDataDaCompraBetween(Long idLoja, LocalDateTime start, LocalDateTime end);

}
