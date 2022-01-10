package br.com.lecom.storemanager.loja.repository;

import br.com.lecom.storemanager.loja.entity.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LojaRepository extends JpaRepository<Loja, Long> {
    Optional<Loja> findByCnpj(String cnpj);

    Optional<Loja> findByRazaoSocial(String razaoSocial);
}