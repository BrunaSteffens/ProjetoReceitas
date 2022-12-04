package com.example.projetoreceitas.projeto.repository;

import com.example.projetoreceitas.projeto.model.Receita;

import java.util.List;

public interface ReceitaRepositorioInterface {
    public List<Receita> getReceitas();
    public Receita getReceitaById(long id);
    public Receita getReceitabyNome(String nome);
}
