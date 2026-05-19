package com.example.frete;

public class Trem implements Transporte {

    @Override
    public void exibirFrete() {
        double valor = FreteCache.getInstance().getValorFrete("TREM");
        System.out.println("Frete por trem: R$ " + valor);
    }

    @Override
    public String getTipo() {
        return "TREM";
    }
}
