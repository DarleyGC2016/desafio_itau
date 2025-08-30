package br.com.desafio.backend.desafio.DTO;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransacaoDTO {
    private Double valor;
    
    private OffsetDateTime dataHora;

    public  boolean validaTempo() {
        OffsetDateTime agora = OffsetDateTime.now();
        return getDataHora().isBefore(agora) && getDataHora().getYear() == agora.getYear() && getDataHora().getMonth() == agora.getMonth() && getDataHora().getDayOfMonth() != agora.getDayOfMonth() || getDataHora().getYear() < agora.getYear() || getDataHora().getMonthValue() < agora.getMonthValue()  || getDataHora().getDayOfMonth() < agora.getDayOfMonth();
    }

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
