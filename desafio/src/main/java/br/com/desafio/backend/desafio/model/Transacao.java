package br.com.desafio.backend.desafio.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transacao {

     private Long id;
     private double valor;
     private OffsetDateTime dataHora;
     
     public Transacao() {
          
     }

     public Transacao(double valor, OffsetDateTime dataHora) {
          this.valor = valor;
          this.dataHora = dataHora;
     }

     @Override
     public String toString() {
          return "Transacao [getDataHora()=" + getDataHora() + ", getId()=" + getId() + ", getValor()=" + getValor()
                    + "]";
     }




     
     
}
