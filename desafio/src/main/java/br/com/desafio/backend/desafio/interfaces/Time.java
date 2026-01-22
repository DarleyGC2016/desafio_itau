package br.com.desafio.backend.desafio.interfaces;

import java.time.OffsetDateTime;

public interface Time {
     default boolean validaTempo(OffsetDateTime dataHora){
          OffsetDateTime agora = OffsetDateTime.now();
          return dataHora.isAfter(agora) && dataHora.isBefore(agora) && dataHora.getYear() == agora.getYear() && dataHora.getMonth() == agora.getMonth() && dataHora.getDayOfMonth() != agora.getDayOfMonth() || dataHora.getYear() < agora.getYear() || dataHora.getMonthValue() < agora.getMonthValue()  || dataHora.getDayOfMonth() < agora.getDayOfMonth() ;
     };
}
