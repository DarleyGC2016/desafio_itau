package br.com.desafio.backend.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.interfaces.Time;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.service.TransacaoService;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
@Setter
@RestController
@RequestMapping("/api/desafio")
public class TransacaoRest implements Time{

    @Autowired
    private TransacaoService transacaoService;

    private List<Transacao> transacoes;

    @PostMapping("/transacao")
    // public ResponseEntity<String> cadastroTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) {
    public ResponseEntity<String> cadastroTransacao(TransacaoDTO transacaoDTO) {
        return transacaoService.save(transacaoDTO);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<List<Transacao>> excluirTodasTransacoes() {
         setTransacoes(transacaoService.deleteAll());
        return  ResponseEntity.ok(getTransacoes());
    }

    @GetMapping("/transacoes")
    public  ResponseEntity<Estatistica> getEstatisticas() {
        return ResponseEntity.ok(transacaoService.getEstatisticas(60));
    }
    

}
