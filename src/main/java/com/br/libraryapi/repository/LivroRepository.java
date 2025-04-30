package com.br.libraryapi.repository;

import com.br.libraryapi.model.Autor;
import com.br.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //metodo de consulta

    List<Livro> findByAutor(Autor autor);
}