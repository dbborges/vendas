package br.com.lecom.transportadora.transportadora.repository;

import br.com.lecom.transportadora.transportadora.entity.HistoricoOrdemEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface HistoricoOrdemEntregaRepository extends JpaRepository<HistoricoOrdemEntrega, Long> {

}
