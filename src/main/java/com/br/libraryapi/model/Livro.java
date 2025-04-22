package com.br.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

//mapeamento de entidades JPA

@Entity
@Table(name = "livro") //nao e obrigatorio
@Data

public class Livro {

    @Id
    @Column(name = "id")// nao e obrigatorio
    @GeneratedValue(strategy = GenerationType.UUID) //id gerado automaticamente
    private UUID id;

    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;
    //private BigDecimal preco;

    //mapeamento com relacionamento
    @ManyToOne //(cascade = CascadeType.ALL) //muitos livros para um autor
    @JoinColumn(name = "id_autor")
    private Autor autor;
}