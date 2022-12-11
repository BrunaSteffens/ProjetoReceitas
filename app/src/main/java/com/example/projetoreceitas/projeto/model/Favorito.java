package com.example.projetoreceitas.projeto.model;

public class Favorito {
    private int favorito_id, user_id;
    private long receita_id;

    public Favorito(int favorito_id, long receita_id, int usuario_id) {
        this.favorito_id = favorito_id;
        this.receita_id = receita_id;
        this.user_id = usuario_id;

    }

    public Favorito(long receita_id, int user_id) {
        this.user_id = user_id;
        this.receita_id = receita_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public long getReceita_id() {
        return receita_id;
    }

}
