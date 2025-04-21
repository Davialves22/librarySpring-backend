package com.br.libraryapi.repository;

import com.br.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("JkRolly");
        autor.setNacionalidade("Britanica");
        autor.setDataNascimento(LocalDate.of(1965, 7, 31));
        var AutorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + AutorSalvo);
    }

    @Test
    public void autalizarTeste() {
        var id = UUID.fromString("c66d7b21-3376-4aa4-8378-f67057acf6bd");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("J. K. Rowling");

            repository.save(autorEncontrado);
        }
    }
}