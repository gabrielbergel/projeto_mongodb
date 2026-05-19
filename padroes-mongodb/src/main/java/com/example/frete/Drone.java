package com.example.frete;

public class Drone implements Transporte {

    @Override
    public void exibirFrete() {
        double valor = FreteCache.getInstance().getValorFrete("DRONE");
        System.out.println("Frete por drone: R$ " + valor);
    }

    @Override
    public String getTipo() {
        return "DRONE";
    }
}
