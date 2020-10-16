package com.banking.demodigitalbankingapi.domain.service;

import com.banking.demodigitalbankingapi.domain.exception.NegocioException;
import com.banking.demodigitalbankingapi.domain.model.Cliente;
import com.banking.demodigitalbankingapi.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente) {
        Cliente emailExistente = clienteRepository.findByEmail(cliente.getEmail());
        Cliente cpfExistente = clienteRepository.findByCpf(cliente.getCpf());

        if (emailExistente != null && !emailExistente.equals(cliente)) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        } else if (cpfExistente != null && !cpfExistente.equals(cliente)) {
            throw  new NegocioException("Já existe um cliente cadastrado com este CPF");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
