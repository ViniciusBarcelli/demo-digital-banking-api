package com.banking.demodigitalbankingapi.domain.repository;

import com.banking.demodigitalbankingapi.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);

    Cliente findByEmail(String email);

    Cliente findByCpf(String cpf);
}
