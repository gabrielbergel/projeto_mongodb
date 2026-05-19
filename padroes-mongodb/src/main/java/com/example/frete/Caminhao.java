package com.example.frete;

public class Caminhao implements Transporte {

    @Override
    public void exibirFrete() {
        double valor = FreteCache.getInstance().getValorFrete("CAMINHAO");
        System.out.println("Frete por caminhão: R$ " + valor);
    }

    @Override
    public String getTipo() {
        return "CAMINHAO";
    }
}
