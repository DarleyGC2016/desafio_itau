package br.com.desafio.backend.desafio.interfaces;

public interface Crud<P, R> {
    R save(P p);
}
