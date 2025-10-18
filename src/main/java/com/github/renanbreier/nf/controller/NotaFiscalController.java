package com.github.renanbreier.nf.controller;

import com.github.renanbreier.nf.model.Cliente;
import com.github.renanbreier.nf.model.NotaFiscal;
import com.github.renanbreier.nf.repository.ClienteRepository;
import com.github.renanbreier.nf.repository.NotaFiscalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nota")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalRepository notaFiscalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<NotaFiscal>> findAll() {
        return ResponseEntity.ok().body(notaFiscalRepository.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<NotaFiscal> notaFiscal = notaFiscalRepository.findById(id);

        if (notaFiscal.isEmpty()) {
            return ResponseEntity.status(404).body("Nota Fiscal com ID " + id + " não encontrada");
        }
        return ResponseEntity.ok().body(notaFiscal.get());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody NotaFiscal notaFiscal) {
        try {
            Cliente cliente = (Cliente) clienteRepository.findByCodigo(notaFiscal.getCliente().getCodigo())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente com código " + notaFiscal.getCliente().getCodigo() + " não encontrado"));

            notaFiscal.setCliente(cliente);
            NotaFiscal novaNota = notaFiscalRepository.save(notaFiscal);
            return ResponseEntity.status(201).body(novaNota);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao cadastrar a nota fiscal: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody NotaFiscal notaAtualizada) {
        try {
            return notaFiscalRepository.findById(id)
                    .<ResponseEntity<?>>map(nota -> {
                        nota.setNumeroNota(notaAtualizada.getNumeroNota());
                        nota.setDataEmissao(notaAtualizada.getDataEmissao());

                        if (notaAtualizada.getCliente() != null &&
                                notaAtualizada.getCliente().getCodigo() != null) {
                            Cliente cliente = (Cliente) clienteRepository.findByCodigo(notaAtualizada.getCliente().getCodigo())
                                    .orElseThrow(() -> new RuntimeException(
                                            "Cliente com código " + notaAtualizada.getCliente().getCodigo() + " não encontrado"));
                            nota.setCliente(cliente);
                        }

                        NotaFiscal notaSalva = notaFiscalRepository.save(nota);
                        return ResponseEntity.ok(notaSalva);
                    })
                    .orElseGet(() -> ResponseEntity.status(404)
                            .body("Nota Fiscal com ID " + id + " não encontrada"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar a nota fiscal: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!notaFiscalRepository.existsById(id)) {
            return ResponseEntity.status(404).body("Nota Fiscal com ID " + id + " não encontrada");
        }

        notaFiscalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
