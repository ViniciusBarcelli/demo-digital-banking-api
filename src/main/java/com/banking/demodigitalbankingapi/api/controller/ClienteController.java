package com.banking.demodigitalbankingapi.api.controller;

import com.banking.demodigitalbankingapi.domain.model.Cliente;
import com.banking.demodigitalbankingapi.domain.repository.ClienteRepository;
import com.banking.demodigitalbankingapi.domain.service.CadastroClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId,
                                             @RequestBody Cliente cliente) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> remover(@PathVariable Long clienteId) {

        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        cadastroClienteService.excluir(clienteId);

        return ResponseEntity.noContent().build();
    }

}
