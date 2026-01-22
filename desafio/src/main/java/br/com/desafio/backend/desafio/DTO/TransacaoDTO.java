package br.com.desafio.backend.desafio.DTO;

import java.time.OffsetDateTime;

import br.com.desafio.backend.desafio.interfaces.Time;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO implements Time {
    private Double valor;

    private OffsetDateTime dataHora;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TransacaoDTO\n  ")
                .append("valor: ")
                .append(getValor())
                .append("\n  ")
                .append("dataHora: ").append(dataHora)
                .append("\n");
        return super.toString();
    }

}
