package com.br.libraryapi.modelo.livro;

import com.br.libraryapi.api.livro.LivroRequest; // Importa o DTO de entrada
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    // Método para salvar o livro, agora recebendo o DTO LivroRequest
    @Transactional
    public Livro save(LivroRequest request) throws IOException {

        // Converte o DTO em entidade Livro
        Livro livro = request.build();

        // converter imagem para byte[]
        if (request.getImagem() != null && !request.getImagem().isEmpty()) {
            livro.setImagem(request.getImagem().getBytes());
        }

        // converte PDF para byte[]
        if (request.getPdf() != null && !request.getPdf().isEmpty()) {
            livro.setPdf(request.getPdf().getBytes());
        }

        // Marcar como habilitado por padrão
        livro.setHabilitado(Boolean.TRUE);

        // Salvar no banco de dados
        return repository.save(livro);
    }

    // Retornar todos os livros cadastrados
    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    // Busca um livro pelo ID (UUID), lança exceção se não encontrar
    public Livro obterPorID(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
}
