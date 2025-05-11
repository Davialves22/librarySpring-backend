package com.br.libraryapi.controller;

import com.br.libraryapi.model.Autor;
import com.br.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    // Criar um autor
    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody Autor autor) {
        Autor novoAutor = autorRepository.save(autor);
        return new ResponseEntity<>(novoAutor, HttpStatus.CREATED);
    }

    // Listar todos os autores
    @GetMapping
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Buscar autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutor(@PathVariable UUID id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Atualizar autor
    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizarAutor(@PathVariable UUID id, @RequestBody Autor autor) {
        if (autorRepository.existsById(id)) {
            autor.setId(id);
            Autor autorAtualizado = autorRepository.save(autor);
            return ResponseEntity.ok(autorAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Deletar autor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable UUID id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
