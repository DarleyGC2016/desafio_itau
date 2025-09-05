package br.com.desafio.backend.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Getter
@Setter
@RestController
@RequestMapping("/api/desafio")
public class TransacaoRest {

    @Autowired
    private TransacaoService transacaoService;

    private List<Transacao> transacoes;

    @PostMapping("/transacao")
    public ResponseEntity<String> cadastroTransacao(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        if (transacaoDTO.getValor() < 0) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Não é permitido valor negativo");
            
        } else if (transacaoDTO.getValor() >= -1 && transacaoDTO.validaTempo()) {

            setTransacoes(transacaoService.addTransacao(transacaoDTO));

            return ResponseEntity.status(HttpStatus.CREATED).body("Transação cadastrada com sucesso");
        } else {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar transação");
        }
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
