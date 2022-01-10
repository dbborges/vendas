package br.com.lecom.transportadora.transportadora.repository;

import br.com.lecom.transportadora.transportadora.entity.OrdemEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdemEntregaRepository extends JpaRepository<OrdemEntrega, Long> {

    Optional<OrdemEntrega> findByCodigoRastreamento(String codigoRastreamento);

    Optional<OrdemEntrega> findByIdVenda(Long idVenda);

}