package br.com.desafio.backend.desafio.DTO;

import java.time.OffsetDateTime;


import jakarta.validation.constraints.NotNull;

public record TransacaoRequest(
        @NotNull(message = "O valor da transação é obrigatório")
        Double valor,
        @NotNull(message = "A data e hora da transação é obrigatória")
        OffsetDateTime dataHora) {

}
