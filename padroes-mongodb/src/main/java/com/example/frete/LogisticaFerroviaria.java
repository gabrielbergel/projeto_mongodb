package com.example.frete;

public class LogisticaFerroviaria extends Logistica {

    @Override
    public Transporte criarTransporte() {
        return new Trem();
    }
}
