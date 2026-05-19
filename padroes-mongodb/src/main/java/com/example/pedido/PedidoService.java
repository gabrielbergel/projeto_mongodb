package com.example.pedido;

import com.example.database.MongoConnection;
import com.example.frete.Transporte;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.time.LocalDateTime;

public class PedidoService {

    private final MongoCollection<Document> collection;

    public PedidoService() {
        collection = MongoConnection.getDatabase().getCollection("pedidos");
    }

    public void salvarPedido(String cliente, Transporte transporte, double valorFrete) {
        Document document = new Document("cliente", cliente)
                .append("tipoFrete", transporte.getTipo())
                .append("valorFrete", valorFrete)
                .append("dataPedido", LocalDateTime.now().toString());

        collection.insertOne(document);

        System.out.println("Pedido salvo no MongoDB.");
    }
}
