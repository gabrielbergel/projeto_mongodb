package com.example.frete;

public abstract class Logistica {

    public abstract Transporte criarTransporte();

    public void calcularFrete() {
        Transporte transporte = criarTransporte();
        transporte.exibirFrete();
    }
}
