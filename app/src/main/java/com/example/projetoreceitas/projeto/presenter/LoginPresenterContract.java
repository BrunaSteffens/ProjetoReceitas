package com.example.projetoreceitas.projeto.presenter;

import android.app.Activity;

import com.example.projetoreceitas.projeto.model.Usuario;


public class LoginPresenterContract {

    //Criando as interfaces, que são os 'contratos' para serem implementados por alguém.
    //Um é o método que vai apresentar a mensagem e o outro é o método que vai pegar os
    // dados da activity, levar até o método de login e executá-lo para checar o login

    public interface view{
        Activity getActivity();

    }

    public interface presenter{
        void checkLogin(String login,String password);
        void validLogin(Usuario usuario);
    }
}
