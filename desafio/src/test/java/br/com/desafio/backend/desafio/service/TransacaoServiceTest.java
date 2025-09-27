package br.com.desafio.backend.desafio.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.desafio.backend.desafio.model.Transacao;


public class TransacaoServiceTest {
    @Mock
    private List<Transacao> transacoes = new ArrayList<>();

    @InjectMocks
    private TransacaoService transacaoService;

    @Test
    void getEstatisticas() {
        transacaoService.getEstatisticas(60);
    }
}
