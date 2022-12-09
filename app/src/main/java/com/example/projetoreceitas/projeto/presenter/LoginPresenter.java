package com.example.projetoreceitas.projeto.presenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;
import com.example.projetoreceitas.projeto.view.HomeActivity;
import com.example.projetoreceitas.projeto.view.ReceitaActivity;


public class LoginPresenter implements LoginPresenterContract.presenter {
    private LoginPresenterContract.view view;

    public LoginPresenter(LoginPresenterContract.view view){
        this.view = view;
    }

    @Override
    public void checkLogin(String email, String password) {
        UsuarioRepositorio repo = UsuarioRepositorio.getInstance(view.getActivity());
        Usuario u = repo.getUserByEmail(email);
        if (u== null || !u.getPassword().equals(password)){
            Toast.makeText(view.getActivity(), "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
            //Testar usar dentro da mensagem: Resources.getSystem().getString(R.string.userpassinvalid)
        } else {
            validLogin(u);
        }
    }

    @Override
    public void validLogin(Usuario usuario) {
        Intent intent = new Intent(view.getActivity(), HomeActivity.class);
        intent.putExtra("usuarioObj", usuario);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(view.getActivity());
        preferences.edit().putInt("usuario_id", usuario.getId()).commit();

        view.getActivity().startActivity(intent);
    }
}
