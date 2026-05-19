package com.example.usuario;

import com.example.database.MongoConnection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class UserCache {

    private static UserCache instance;

    private final Map<String, User> cache;
    private final MongoCollection<Document> collection;

    private static final long TEMPO_EXPIRACAO = 60_000;

    private UserCache() {
        cache = new HashMap<>();
        collection = MongoConnection.getDatabase().getCollection("usuarios");
    }

    public static UserCache getInstance() {
        if (instance == null) {
            instance = new UserCache();
        }

        return instance;
    }

    public void addUser(String username, String email) {
        User user = new User(username, email);
        cache.put(username, user);

        Document document = new Document("username", username)
                .append("email", email);

        collection.insertOne(document);

        System.out.println("Usuário adicionado no cache e no MongoDB.");
    }

    public void removeUser(String username) {
        cache.remove(username);
        collection.deleteOne(eq("username", username));

        System.out.println("Usuário removido do cache e do MongoDB.");
    }

    public User getUserByUsername(String username) {
        User user = cache.get(username);

        if (user != null && !cacheExpirado(user)) {
            System.out.println("Usuário encontrado no cache.");
            return user;
        }

        if (user != null) {
            cache.remove(username);
            System.out.println("Cache expirado. Buscando no MongoDB.");
        }

        Document document = collection.find(eq("username", username)).first();

        if (document != null) {
            User userFromMongo = new User(
                    document.getString("username"),
                    document.getString("email")
            );

            cache.put(username, userFromMongo);

            System.out.println("Usuário encontrado no MongoDB e salvo no cache.");
            return userFromMongo;
        }

        System.out.println("Usuário não encontrado.");
        return null;
    }

    private boolean cacheExpirado(User user) {
        long tempoAtual = System.currentTimeMillis();
        return tempoAtual - user.getCacheTime() > TEMPO_EXPIRACAO;
    }
}
