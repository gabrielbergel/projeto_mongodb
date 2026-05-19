package com.example.frete;

public class LogisticaMaritima extends Logistica {

    @Override
    public Transporte criarTransporte() {
        return new Navio();
    }
}
