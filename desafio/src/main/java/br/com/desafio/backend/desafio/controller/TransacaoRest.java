package br.com.desafio.backend.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.desafio.DTO.TransacaoRequest;
import br.com.desafio.backend.desafio.DTO.TransacaoResponse;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.service.TransacaoService;

import jakarta.validation.Valid;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/desafio")
public class TransacaoRest {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao")
    public ResponseEntity<String> cadastroTransacao(@RequestBody @Valid TransacaoRequest transacaoRequest) {

        TransacaoResponse transacao = new TransacaoResponse(
                transacaoRequest.valor(),
                transacaoRequest.dataHora());
        Map<Integer, String> response = transacaoService.save(transacao);
        return ResponseEntity.status(response.keySet().hashCode()).body(response.values().iterator().next());
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<String> excluirTodasTransacoes() {
        transacaoService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Exclusão de todas as transações realizada com sucesso");
    }

    @GetMapping("/transacoes")
    public ResponseEntity<Estatistica> getEstatisticas() {
        return ResponseEntity.ok(transacaoService.getEstatisticas(60));
    }

}
