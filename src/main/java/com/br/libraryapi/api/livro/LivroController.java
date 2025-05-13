package com.br.libraryapi.api.livro;

import com.br.libraryapi.modelo.livro.Livro;
import com.br.libraryapi.modelo.livro.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Salvar um livro via multipart/form-data com imagem e PDF
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Livro> save(@ModelAttribute LivroRequest request) throws IOException {
        Livro livro = livroService.save(request);
        return new ResponseEntity<>(livro, HttpStatus.CREATED);
    }

    // Listar todos os livros
    @GetMapping
    public List<Livro> listarTodos() {
        return livroService.listarTodos();
    }

    // Buscar livro por ID (UUID)
    @GetMapping("/{id}")
    public Livro obterPorID(@PathVariable UUID id) {
        return livroService.obterPorID(id);
    }

    // Retornar PDF do livro por ID
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> obterPdf(@PathVariable UUID id) {
        Livro livro = livroService.obterPorID(id);
        if (livro.getPdf() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(livro.getPdf());
    }

    // Retornar imagem do livro por ID
    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable UUID id) {
        Livro livro = livroService.obterPorID(id);
        if (livro.getImagem() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(livro.getImagem());
    }
}
