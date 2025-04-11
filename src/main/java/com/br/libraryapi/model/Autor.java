package com.br.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

//mapeamento de entidades JPA

@Entity
@Table(name = "autor", schema = "public") //nao e obrigatorio
@Getter
@Setter

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


}