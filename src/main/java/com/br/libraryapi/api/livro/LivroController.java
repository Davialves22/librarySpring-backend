package com.br.libraryapi.api.livro;

import com.br.libraryapi.model.Livro;
import com.br.libraryapi.model.Autor;
import com.br.libraryapi.model.GeneroLivro;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/livros")
@CrossOrigin

public class LivroController {

    @Autowired
    private LivroService LivroService;

    @PostMapping

    public ResponseEntity<Livro> save(@RequestBody LivroRequest request)

    cadastrarLivro(
            @RequestParam("isbn") String isbn,
            @RequestParam("titulo") String titulo,
            @RequestParam("dataPublicacao") String dataPublicacao,
            @RequestParam("genero") String genero,
            @RequestParam("preco") BigDecimal preco,
            @RequestParam("autorId") UUID autorId,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem,
            @RequestParam(value = "pdf", required = false) MultipartFile pdf) throws IOException {

        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setDataPublicacao(LocalDate.parse(dataPublicacao));
        livro.setGenero(GeneroLivro.valueOf(genero));
        livro.setPreco(preco);

        Optional<Autor> autorOptional = autorRepository.findById(autorId);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        livro.setAutor(autorOptional.get());

        if (imagem != null && !imagem.isEmpty()) {
            livro.setImagem(imagem.getBytes());
        }

        if (pdf != null && !pdf.isEmpty()) {
            livro.setPdf(pdf.getBytes());
        }

        Livro livroSalvo = livroRepository.save(livro);

        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> obterPdf(@PathVariable UUID id) {
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

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
        Livro livro = livroRepository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        byte[] imagem = livro.getImagem();
        if (imagem == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imagem);
    }
}
