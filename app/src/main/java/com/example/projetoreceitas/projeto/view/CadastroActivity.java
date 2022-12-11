package com.example.projetoreceitas.projeto.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.presenter.LoginPresenter;
import com.example.projetoreceitas.projeto.presenter.LoginPresenterContract;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;

public class CadastroActivity extends AppCompatActivity  implements LoginPresenterContract.view{
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.presenter = new LoginPresenter(this);


        findViewById(R.id.signUpConfirm).setOnClickListener(
                v -> {
                    Usuario usuario = new Usuario();
                    usuario.setName(((TextView) findViewById(R.id.signUpName)).getText().toString());
                    usuario.setEmail(((TextView) findViewById(R.id.signUpEmail)).getText().toString());
                    usuario.setPassword(((TextView) findViewById(R.id.signUpPassword)).getText().toString());

                    UsuarioRepositorio.getInstance().addUser(usuario);
                    presenter.checkLogin(usuario.getEmail(), usuario.getPassword());
                }
        );
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}