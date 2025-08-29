package br.com.desafio.backend.desafio.parse;

import br.com.desafio.backend.desafio.DTO.TransacaoDTO;
import br.com.desafio.backend.desafio.model.Transacao;

public class TransacaoParse {

    public static TransacaoParse get() {
        return new TransacaoParse();
    }

    public Transacao toEntity(TransacaoDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setValor(dto.getValor());
        transacao.setDataHora(dto.getDataHora());
        return transacao;
    }
}
