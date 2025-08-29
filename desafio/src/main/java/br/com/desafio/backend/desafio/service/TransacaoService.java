package br.com.desafio.backend.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.model.Transacao;
import br.com.desafio.backend.desafio.parse.TransacaoParse;

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

}
