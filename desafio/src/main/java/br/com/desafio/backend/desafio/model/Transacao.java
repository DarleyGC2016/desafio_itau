package br.com.desafio.backend.desafio.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_transacao")
public class Transacao {
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transacao_seq")
     @SequenceGenerator(name = "transacao_seq", sequenceName = "transacao_seq", allocationSize = 1)
     private Long id;
     @Column(name = "valor", nullable = false)
     private Double valor;
     @Column(name = "data_hora", nullable = false)
     private OffsetDateTime dataHora;
     

     @Override
     public String toString() {
          return "Transacao [Valor: "+ getValor() + 
          " DataHora: " + getDataHora() + "]";
     }
}
