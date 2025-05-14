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

@RestController
@RequestMapping("/api/livro")
@CrossOrigin
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Criar livro (com imagem e PDF)
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

    // Buscar livro por ID
    @GetMapping("/{id}")
    public Livro obterPorID(@PathVariable Long id) {
        return livroService.obterPorID(id);
    }

    // Buscar PDF do livro
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> obterPdf(@PathVariable Long id) {
        Livro livro = livroService.obterPorID(id);
        if (livro.getPdf() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(livro.getPdf());
    }

    // Buscar imagem do livro
    @GetMapping("/imagem/{id}")
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        Livro livro = livroService.obterPorID(id);
        if (livro.getImagem() == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(livro.getImagem());
    }

    // Atualizar livro (usando multipart)
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(@PathVariable Long id, @ModelAttribute LivroRequest request) throws IOException {
        livroService.update(id, request);
        return ResponseEntity.ok().build();
    }

    // Deletar livro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.ok().build();
    }
}
