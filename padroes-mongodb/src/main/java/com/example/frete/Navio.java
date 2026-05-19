package com.example.frete;

public class Navio implements Transporte {

    @Override
    public void exibirFrete() {
        double valor = FreteCache.getInstance().getValorFrete("NAVIO");
        System.out.println("Frete por navio: R$ " + valor);
    }

    @Override
    public String getTipo() {
        return "NAVIO";
    }
}
