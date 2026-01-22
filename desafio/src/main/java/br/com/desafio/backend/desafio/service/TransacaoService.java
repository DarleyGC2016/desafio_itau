package br.com.desafio.backend.desafio.service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.interfaces.Crud;
import br.com.desafio.backend.desafio.interfaces.Time;
import br.com.desafio.backend.desafio.interfaces.TransacaoValid;
import br.com.desafio.backend.desafio.model.Estatistica;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.parse.TransacaoParse;
import br.com.desafio.backend.desafio.repository.TransacaoRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class TransacaoService implements Crud<TransacaoDTO, ResponseEntity<String>>, Time, TransacaoValid {
     
  
    @Autowired
    private TransacaoRepository transacaoRepository;

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

    @Override
    public ResponseEntity<String> save(TransacaoDTO transacaoDTO) {

        if (transacaoDTO.getValor() < 0) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body("Não é permitido valor negativo");

        } else if (transacaoDTO.getValor() >= -1 && validaTempo(transacaoDTO.getDataHora())) {

            setTransacoes(addTransacao(transacaoDTO));

            return ResponseEntity.status(HttpStatus.CREATED).body("Transação cadastrada com sucesso");
        } else {

            return validarTransacao(transacaoDTO.getValor(), transacaoDTO.getDataHora());
        }
    }

}
