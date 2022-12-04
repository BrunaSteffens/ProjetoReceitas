package com.example.projetoreceitas.projeto.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.presenter.LoginPresenter;
import com.example.projetoreceitas.projeto.presenter.LoginPresenterContract;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;


public class CadastroActivity extends AppCompatActivity  implements LoginPresenterContract.view{
    private static final String TAG = "CadastroActivity";
    private LoginPresenterContract.presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.presenter = new LoginPresenter(this);


        findViewById(R.id.signUpConfirm).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Usuario usuario = new Usuario();
                        usuario.setName(((TextView) findViewById(R.id.signUpName)).getText().toString());
                        usuario.setEmail(((TextView) findViewById(R.id.signUpEmail)).getText().toString());
                        usuario.setPassword(((TextView) findViewById(R.id.signUpPassword)).getText().toString());

                        if(UsuarioRepositorio.getInstance().getUserByEmail(usuario.getEmail())!=null){
                            Toast.makeText(getApplicationContext(), "Usuário já cadastrado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);

                        } else{
                            UsuarioRepositorio.getInstance().addUser(usuario);
                            Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                            presenter.checkLogin(usuario.getEmail(), usuario.getPassword());
                        }
                    }
                }
        );
    }

    @Override
    public void message(String msg) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}