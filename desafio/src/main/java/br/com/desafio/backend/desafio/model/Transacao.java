package br.com.desafio.backend.desafio.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transacao {

     private Long id;
     private Double valor;
     private OffsetDateTime dataHora;
     

     @Override
     public String toString() {
          return "Transacao [getDataHora()=" + getDataHora() + ", getId()=" + getId() + ", getValor()=" + getValor()
                    + "]";
     }
}
