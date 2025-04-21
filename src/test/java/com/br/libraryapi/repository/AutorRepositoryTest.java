package com.br.libraryapi.repository;

import com.br.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
}