package br.com.desafio.backend.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.backend.desafio.DTO.TransacaoRequest;
import br.com.desafio.backend.desafio.DTO.TransacaoResponse;
import br.com.desafio.backend.desafio.interfaces.Crud;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.service.TransacaoService;

import jakarta.validation.Valid;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/desafio")
public class TransacaoRest implements Crud<TransacaoRequest, ResponseEntity<String>> {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/transacao")
    @Override
    public ResponseEntity<String> save(@RequestBody @Valid TransacaoRequest transacaoRequest) {

        TransacaoResponse transacao = new TransacaoResponse(
                transacaoRequest.valor(),
                transacaoRequest.dataHora());
        Map<Integer, String> response = transacaoService.save(transacao);
        int httpStatusCode = response
                .keySet()
                .iterator()
                .next();
        String body = response
                .values()
                .iterator()
                .next();
        return ResponseEntity.status(httpStatusCode).body(body);
    }

    @DeleteMapping("/transacao")
    public ResponseEntity<String> excluirTodasTransacoes() {
        transacaoService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("Exclusão de todas as transações realizada com sucesso");
    }

    @GetMapping("/transacoes")
    public ResponseEntity<Estatistica> calculoEstatistico() {
        return ResponseEntity.ok(transacaoService.calculoEstatistico(60));
    }

}
