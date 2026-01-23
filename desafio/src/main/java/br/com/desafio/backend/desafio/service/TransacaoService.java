package br.com.desafio.backend.desafio.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.desafio.backend.desafio.DTO.TransacaoResponse;
import br.com.desafio.backend.desafio.interfaces.Crud;
import br.com.desafio.backend.desafio.interfaces.Time;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.parse.TransacaoParse;
import br.com.desafio.backend.desafio.repository.TransacaoRepository;

@Service
public class TransacaoService implements Crud<TransacaoResponse, Map<Integer,String>>, Time {

    @Autowired
    private TransacaoRepository transacaoRepository;
    
    public Transacao addTransacao(TransacaoResponse transacaoDTO) {
        return transacaoRepository.save(TransacaoParse.get().toEntity(transacaoDTO));     
    }    

    public void deleteAll() {
        transacaoRepository.deleteAll();
    }

    public Estatistica getEstatisticas(int segundo) {

        List<Transacao> transacoesFilter = new ArrayList<>();
        List<Transacao> transacoes = transacaoRepository.findAll();
        
        boolean zeroEstatistica = false;
        int timeDesc = 0;

        if (transacoes.isEmpty()) {

            return new Estatistica();

        } else {

            for (Transacao transacao : transacoes) {

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

    @Override
    public Map<Integer,String> save(TransacaoResponse transacaoDTO) {
         Map<Integer,String> message = new HashMap<>();
        if (transacaoDTO.valor() < 0) {
            message.put(HttpStatus.UNPROCESSABLE_CONTENT.value(), "Não é permitido valor negativo");
            return message;

        } else if (transacaoDTO.valor() >= -1 && validaTempo(transacaoDTO.dataHora())) {

            addTransacao(transacaoDTO);
            message.put(HttpStatus.CREATED.value(), "Transação cadastrada com sucesso");
            return message; 
        }
        return null; 
    }

}
