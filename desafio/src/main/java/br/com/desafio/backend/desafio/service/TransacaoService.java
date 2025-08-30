package br.com.desafio.backend.desafio.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.parse.TransacaoParse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class TransacaoService {

    private List<Transacao> transacoes = new ArrayList<>();

    public List<Transacao> addTransacao(TransacaoDTO transacaoDTO) {

        transacoes.add(TransacaoParse.get().toEntity(transacaoDTO));
        return transacoes;
    }

    public List<Transacao> deleteAll() {
        transacoes.clear();
        return transacoes;
    }

    public Estatistica getEstatisticas(int segundo) {
        
        if(!transacoes.isEmpty()) {
            List<Transacao> transacoesFilter = this.transacoes
            .stream()
            .filter(tempo -> tempo.getDataHora().getSecond() >= 0 && tempo.getDataHora().getSecond() < segundo)
            .toList();
            DoubleSummaryStatistics estatisticas = transacoesFilter.stream()
                    .mapToDouble(Transacao::getValor)
                    .summaryStatistics();
    
            Estatistica estatistica = new Estatistica();
            estatistica.setCount(estatisticas.getCount());
            estatistica.setSum(estatisticas.getSum());
            estatistica.setAvg(estatisticas.getAverage());
            estatistica.setMin(estatisticas.getMin());
            estatistica.setMax(estatisticas.getMax());
            return estatistica;
        }
        return null;
    }

}
