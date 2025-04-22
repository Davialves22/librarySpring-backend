package com.br.libraryapi.repository;

import com.br.libraryapi.model.Autor;
import com.br.libraryapi.model.GeneroLivro;
import com.br.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {
    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9780545069670");
        livro.setPreco(BigDecimal.valueOf(39, 93));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter e a Pedra Filosofal");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));

        Autor autor = autorRepository
                .findById(UUID.fromString("acf61694-43ec-46a5-b186-89573ade3fe8"))
                .orElse(null);

        livro.setAutor(new Autor());

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("9780545069670");
        livro.setPreco(BigDecimal.valueOf(39, 93));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter e o Prisioneiro de Askaban");
        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));

        Autor autor = new Autor();
        autor.setNome("JkRolly");
        autor.setNacionalidade("Britanica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));

        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("5d6431c6-971d-4bff-bea5-eb1c26207138");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("acf61694-43ec-46a5-b186-89573ade3fe8");
        autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setTitulo("Harry Potter e a Câmara Secreta");
        livroParaAtualizar.setPreco(BigDecimal.valueOf(42, 18));
        livroParaAtualizar.setDataPublicacao(LocalDate.of(1998, 7, 2));

        repository.save(livroParaAtualizar);
    }

    @Test
    void deletar() {
        UUID id = UUID.fromString("acf61694-43ec-46a5-b186-89573ade3fe8");
        repository.deleteById(id);
    }

    //operações com cascade
//    @Test
//    void salvarCascadeTest() {
//        Livro livro = new Livro();
//        livro.setIsbn("9780545069670");
//        livro.setPreco(BigDecimal.valueOf(39, 93));
//        livro.setGenero(GeneroLivro.FANTASIA);
//        livro.setTitulo("Harry Potter e a Pedra Filosofal");
//        livro.setDataPublicacao(LocalDate.of(1997, 6, 26));
//
//        Autor autor = new Autor();
//        autor.setNome("JkRolly");
//        autor.setNacionalidade("Britanica");
//        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
//
//        //para salvar o autor junto com o livro
////        autorRepository.save(autor);
////
////        livro.setAutor(autor);
////
////        repository.save(livro);
//
//        //para salvar o livro
//
//        livro.setAutor(autor);
//
//        repository.save(livro);
//    }
}