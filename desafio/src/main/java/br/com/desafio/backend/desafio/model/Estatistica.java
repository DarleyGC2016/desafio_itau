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
        return doubleFormat(avg);
    }

    public Double getSum() {
        return doubleFormat(sum);
    }

    public Double getMax() {
        return doubleFormat(max);
    }

    public Double getMin() {
        return doubleFormat(min);
    }

    public Estatistica() {
        setCount(0L);
        setSum(0.0);
        setAvg(0.0);
        setMin(0.0);
        setMax(0.0);
    }

    public Estatistica(Long count, Double sum, Double avg, Double min, Double max) {
        setCount(count);
        setSum(sum);
        setAvg(avg);
        setMin(min);
        setMax(max);
    }

    private Double doubleFormat(Double valor) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formatado = decimalFormat.format(valor);
        return Double.parseDouble(formatado.replace(",", "."));
    }

}
