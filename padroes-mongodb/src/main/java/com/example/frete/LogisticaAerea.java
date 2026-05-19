package com.example.frete;

public class LogisticaAerea extends Logistica {

    @Override
    public Transporte criarTransporte() {
        return new Drone();
    }
}
