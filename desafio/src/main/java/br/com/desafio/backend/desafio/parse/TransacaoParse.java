package br.com.desafio.backend.desafio.parse;

import br.com.desafio.backend.desafio.DTO.TransacaoResponse;
import br.com.desafio.backend.desafio.model.Transacao;

public class TransacaoParse {

    public static TransacaoParse get() {
        return new TransacaoParse();
    }

    public Transacao toEntity(TransacaoResponse dto) {
        Transacao transacao = new Transacao();
        transacao.setValor(dto.valor());
        transacao.setDataHora(dto.dataHora());
        return transacao;
    }
}
