package br.com.desafio.backend.desafio.DTO;

import java.time.OffsetDateTime;

public record TransacaoResponse( Double valor, OffsetDateTime dataHora) {
}
