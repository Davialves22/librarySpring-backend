package com.br.libraryapi.api.livro;

import com.br.libraryapi.modelo.livro.GeneroLivro;
import com.br.libraryapi.modelo.livro.Livro;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LivroRequest {

    private String isbn;
    private String titulo;
    private LocalDate dataPublicacao;
    private GeneroLivro genero;
    private BigDecimal preco;

    // Dados do autor embutidos (tudo será salvo na mesma tabela)
    private String nomeAutor;
    private String nacionalidadeAutor;

    private MultipartFile imagem;    // Para a imagem binária
    private String imagemUrl;        // Para o link da imagem
    private MultipartFile pdf;

    // Método que converte o DTO em entidade Livro
    public Livro build() {
        Livro livro = new Livro();
        livro.setIsbn(isbn);
        livro.setTitulo(titulo);
        livro.setDataPublicacao(dataPublicacao);
        livro.setGenero(genero);
        livro.setPreco(preco);
        livro.setNomeAutor(nomeAutor);
        livro.setNacionalidadeAutor(nacionalidadeAutor);
         livro.setImagemUrl(imagemUrl);  
        
        return livro;
    }
}
