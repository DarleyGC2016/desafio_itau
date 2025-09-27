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

    // private List<Transacao> transacoesFilter = new ArrayList<>();
    public List<Transacao> addTransacao(TransacaoDTO transacaoDTO) {

        transacoes.add(TransacaoParse.get().toEntity(transacaoDTO));
        return transacoes;
    }

    public List<Transacao> deleteAll() {
        transacoes.clear();
        return transacoes;
    }

    public Estatistica getEstatisticas(int segundo) {

        boolean zeroEstatistica = false;
        int timeDesc = 0;
        List<Transacao> transacoesFilter = new ArrayList<>();

        if (transacoes.isEmpty()) {

            return new Estatistica();

        } else {
            
            for (Transacao transacao : getTransacoes()) {

                if (transacao.getDataHora().getSecond() == 0) {

                    zeroEstatistica = true;

                } else {

                    timeDesc = segundo % transacao.getDataHora().getSecond();
                    if (timeDesc <= segundo - 1) {
                        transacoesFilter.add(transacao);
                        zeroEstatistica = false;
                    }
                    
                }
            }

            if (zeroEstatistica) {

                return new Estatistica();

            } else {

                DoubleSummaryStatistics estatisticas = transacoesFilter.stream()
                        .mapToDouble(Transacao::getValor)
                        .summaryStatistics();

                return new Estatistica(
                        estatisticas.getCount(),
                        estatisticas.getSum(),
                        estatisticas.getAverage(),
                        estatisticas.getMin(),
                        estatisticas.getMax());

            }
        }
    }

}
