package com.br.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//mapeamento de entidades JPA

@Entity
@Table(name = "autor", schema = "public") //nao e obrigatorio
@Getter
@Setter
@ToString

public class Autor {

    @Id
    @Column(name = "id")// nao e obrigatorio
    @GeneratedValue(strategy = GenerationType.UUID) //id gerado automaticamente
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor") //um autor pra muitos livros
    private List<Livro> livros;
}