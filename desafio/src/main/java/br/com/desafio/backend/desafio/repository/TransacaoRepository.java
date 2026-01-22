package br.com.desafio.backend.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.backend.desafio.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    // Não sobrescreva o saveAndFlush manualmente aqui, 
    // a menos que seja estritamente necessário.
    
}
