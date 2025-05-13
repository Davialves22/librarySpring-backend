package com.br.libraryapi.repository;

import com.br.libraryapi.modelo.livro.GeneroLivro;
import com.br.libraryapi.modelo.livro.Livro;
import com.br.libraryapi.modelo.livro.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9780545069670");
        livro.setPreco(BigDecimal.valueOf(39, 93));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));

        // Defina o nome do autor diretamente como String
        livro.setNomeAutor("J.K. Rowling"); // Aqui, armazenamos o nome do autor diretamente

        repository.save(livro); // Salvando o livro
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("9780545069670");
        livro.setPreco(BigDecimal.valueOf(39, 93));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter e o Prisioneiro de Askaban");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));

        // Defina o nome do autor diretamente
        livro.setNomeAutor("J.K. Rowling"); // Aqui também, o autor é apenas um nome em String

        repository.save(livro); // Salvando o livro com o autor
    }

    @Test
    void atualizarAutorDoLivro() {
        Long id = 1L; // Usando Long para buscar o livro
        var livroParaAtualizar = repository.findById(id).orElse(null);

        if (livroParaAtualizar != null) {
            livroParaAtualizar.setTitulo("Harry Potter e a Câmara Secreta");
            livroParaAtualizar.setPreco(BigDecimal.valueOf(42, 18));
            livroParaAtualizar.setDataPublicacao(LocalDate.of(1998, 7, 2));
            livroParaAtualizar.setNomeAutor("J.K. Rowling"); // Atualizando o nome do autor
            repository.save(livroParaAtualizar); // Salvando a atualização
        }
    }

    @Test
    void deletar() {
        Long id = 1L; // Usando Long para deletar o livro
        repository.deleteById(id); // Deletando o livro com o ID correto
    }

    @Test
    void buscarLivroTest() {
        Long id = 1L; // Usando Long para buscar o livro
        Livro livro = repository.findById(id).orElse(null);
        if (livro != null) {
            System.out.println("Livro: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getNomeAutor()); // O autor é uma String
        }
    }
}
