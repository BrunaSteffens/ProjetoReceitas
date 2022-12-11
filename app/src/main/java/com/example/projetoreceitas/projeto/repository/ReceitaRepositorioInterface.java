package com.example.projetoreceitas.projeto.repository;

import com.example.projetoreceitas.projeto.model.Receita;

import java.util.List;

public interface ReceitaRepositorioInterface {
    List<Receita> getReceitas();
    Receita getReceitaById(long id);
}
