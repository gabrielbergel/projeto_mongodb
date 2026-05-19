package com.example.frete;

public class LogisticaRodoviaria extends Logistica {

    @Override
    public Transporte criarTransporte() {
        return new Caminhao();
    }
}
