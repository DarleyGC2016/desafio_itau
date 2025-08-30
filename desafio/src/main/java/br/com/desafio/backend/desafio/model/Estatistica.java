package br.com.desafio.backend.desafio.model;

import java.text.DecimalFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Estatistica {
    private Long count;
    private Double sum;
    private Double avg;
    private Double min;
    private Double max;       

    public Double getAvg() {
        return dadoFormatar(avg);
    }  

    public Double getSum() {
        return dadoFormatar(sum);
    }

    public Double getMax() {
        return dadoFormatar(max);
    }

    public Double getMin() {
        return dadoFormatar(min);
    }
    
    private Double dadoFormatar(Double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formatado = decimalFormat.format(valor);
        return Double.parseDouble(formatado.replace(",", "."));
    }

}
