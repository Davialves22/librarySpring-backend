package com.br.libraryapi.api.livro;

import com.br.libraryapi.model.Livro;
import com.br.libraryapi.model.Autor;
import com.br.libraryapi.model.GeneroLivro;
import com.br.libraryapi.service.LivroService;
import com.br.libraryapi.repository.LivroRepository;
import com.br.libraryapi.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<Livro> save(@RequestBody LivroRequest request) {
        Livro livro = livroService.save(request.build());
        return new ResponseEntity<>(livro, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterPorID(@PathVariable UUID id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") UUID id, @RequestBody LivroRequest request) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()) {
            Livro livro = livroService.update(id, request.build());
            return ResponseEntity.ok(livro);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            livroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> obterPdf(@PathVariable UUID id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        byte[] pdf = livro.getPdf();
        if (pdf == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable UUID id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        byte[] imagem = livro.getImagem();
        if (imagem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagem);
    }
}
