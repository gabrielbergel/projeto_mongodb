package com.example.frete;

import com.example.database.MongoConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class FreteCache {

    private static FreteCache instance;

    private final Map<String, Double> cache;
    private final MongoCollection<Document> collection;

    private FreteCache() {
        cache = new HashMap<>();
        collection = MongoConnection.getDatabase().getCollection("fretes");
    }

    public static FreteCache getInstance() {
        if (instance == null) {
            instance = new FreteCache();
        }

        return instance;
    }

    public void salvarFrete(String tipo, String descricao, double valor) {
        cache.put(tipo, valor);

        Document existente = collection.find(eq("tipo", tipo)).first();

        Document document = new Document("tipo", tipo)
                .append("descricao", descricao)
                .append("valor", valor);

        if (existente == null) {
            collection.insertOne(document);
        } else {
            collection.replaceOne(eq("tipo", tipo), document);
        }

        System.out.println("Frete salvo no MongoDB: " + tipo);
    }

    public double getValorFrete(String tipo) {
        if (cache.containsKey(tipo)) {
            System.out.println("Valor do frete obtido do cache.");
            return cache.get(tipo);
        }

        Document document = collection.find(eq("tipo", tipo)).first();

        if (document != null) {
            double valor = document.getDouble("valor");
            cache.put(tipo, valor);

            System.out.println("Valor do frete obtido do MongoDB.");
            return valor;
        }

        throw new RuntimeException("Tipo de frete não encontrado: " + tipo);
    }
}
