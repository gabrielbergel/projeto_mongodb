package com.example.usuario;

public class User {

    private String username;
    private String email;
    private long cacheTime;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.cacheTime = System.currentTimeMillis();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public long getCacheTime() {
        return cacheTime;
    }

    public void atualizarCacheTime() {
        this.cacheTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Usuário: " + username + " | Email: " + email;
    }
}
