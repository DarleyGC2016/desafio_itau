package br.com.desafio.backend.desafio.interfaces;

import java.time.OffsetDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface TransacaoValid{
    default boolean isValorValid(Double valor){
        return valor == null || valor.isNaN();
    }
    default boolean isDataHoraValid(OffsetDateTime dataHora){
        return dataHora == null || dataHora.toString().isEmpty();
    }   
    default ResponseEntity<String> validarTransacao(Double valor, OffsetDateTime dataHora){
        if(!isValorValid(valor) || !isDataHoraValid(dataHora)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar transação");
        }
        return null;
    }
}
