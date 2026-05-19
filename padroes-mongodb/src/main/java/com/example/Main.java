package com.example;

import com.example.frete.*;
import com.example.pedido.PedidoService;
import com.example.usuario.User;
import com.example.usuario.UserCache;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== ATIVIDADE SINGLETON + MONGODB ===");

        UserCache userCache = UserCache.getInstance();

        userCache.addUser("bruno", "bruno@email.com");
        userCache.addUser("ana", "ana@email.com");

        User usuario = userCache.getUserByUsername("bruno");

        if (usuario != null) {
            System.out.println(usuario);
        }

        userCache.removeUser("ana");

        System.out.println();
        System.out.println("=== ATIVIDADE FACTORY METHOD + MONGODB ===");

        FreteCache freteCache = FreteCache.getInstance();

        freteCache.salvarFrete("CAMINHAO", "Frete terrestre por caminhão", 250.00);
        freteCache.salvarFrete("NAVIO", "Frete marítimo por navio", 800.00);
        freteCache.salvarFrete("DRONE", "Frete aéreo por drone", 120.00);
        freteCache.salvarFrete("TREM", "Frete ferroviário por trem", 500.00);

        Logistica logisticaRodoviaria = new LogisticaRodoviaria();
        Logistica logisticaMaritima = new LogisticaMaritima();
        Logistica logisticaAerea = new LogisticaAerea();
        Logistica logisticaFerroviaria = new LogisticaFerroviaria();

        logisticaRodoviaria.calcularFrete();
        logisticaMaritima.calcularFrete();
        logisticaAerea.calcularFrete();
        logisticaFerroviaria.calcularFrete();

        System.out.println();
        System.out.println("=== GRAVAÇÃO DE PEDIDOS ===");

        PedidoService pedidoService = new PedidoService();

        Transporte caminhao = new Caminhao();
        double valorCaminhao = freteCache.getValorFrete(caminhao.getTipo());
        pedidoService.salvarPedido("Cliente Bruno", caminhao, valorCaminhao);

        Transporte drone = new Drone();
        double valorDrone = freteCache.getValorFrete(drone.getTipo());
        pedidoService.salvarPedido("Cliente Ana", drone, valorDrone);
    }
}
