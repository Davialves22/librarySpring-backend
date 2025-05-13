package com.br.libraryapi.repository;

import com.br.libraryapi.model.GeneroLivro;
import com.br.libraryapi.model.Livro.Livro;
import com.br.libraryapi.model.Usuario.Usuario;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;


    @Test
    public void salvarTest() {
        Usuario autor = new Usuario();
        autor.setNome("JkRolly");
        autor.setNacionalidade("Britanica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
        var AutorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + AutorSalvo);
    }

    @Test
    public void autalizarTeste() {
        var id = UUID.fromString("c66d7b21-3376-4aa4-8378-f67057acf6bd");

        Optional<Usuario> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Usuario autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);
            autorEncontrado.setNome("J. K. Rowling");
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Usuario> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de Autores:" + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("c66d7b21-3376-4aa4-8378-f67057acf6bd");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("c66d7b21-3376-4aa4-8378-f67057acf6bd");
        var JK = repository.findById(id).get();
        repository.delete(JK);
    }

    @Test
    void salvarAutorComLivros() {
        Usuario autor = new Usuario();
        autor.setNome("JkRolly");
        autor.setNacionalidade("Britanica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
        var AutorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + AutorSalvo);

        Livro livro = new Livro();
        livro.setIsbn("9780545069670");
        livro.setPreco(BigDecimal.valueOf(67, 71));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter e o Cálice de Fogo");
        livro.setDataPublicacao(LocalDate.of(2000, 7, 8));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);

        repository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("85a0dbad-fc6a-4628-bb37-67d8c34c81ee");
        var autor = repository.findById(id).get();

//buscar os livros do autor

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);

    }
}