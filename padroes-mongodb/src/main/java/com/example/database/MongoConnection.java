package com.example.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private MongoConnection() {}

    public static MongoDatabase getDatabase() {
        if (database == null) {
            String uri = "mongodb+srv://SEU_USUARIO:SUA_SENHA@SEU_CLUSTER.mongodb.net/?retryWrites=true&w=majority";

            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("atividade_padroes");
        }

        return database;
    }
}
