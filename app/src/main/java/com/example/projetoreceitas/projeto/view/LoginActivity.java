package com.example.projetoreceitas.projeto.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.presenter.LoginPresenter;
import com.example.projetoreceitas.projeto.presenter.LoginPresenterContract;
import com.example.projetoreceitas.projeto.repository.ReceitasRepositorio;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {
    private static final String TAG = "LoginActivity";
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView email = findViewById(R.id.loginEmailAddress);
        TextView password = findViewById(R.id.loginPassword);

        UsuarioRepositorio.getInstance(this).addUserTest();
        ReceitasRepositorio.getInstance();
        Log.d(TAG, "onCreate: ");

        this.presenter = new LoginPresenter(this);

        findViewById(R.id.loginConfirm).setOnClickListener(
                v -> {
                    SharedPreferences preferences = getSharedPreferences("dados", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email.getText().toString());
                    editor.apply();

                    presenter.checkLogin(
                            email.getText().toString(),
                            password.getText().toString()
                    );
                }
        );

        SharedPreferences preferences = getSharedPreferences("dados", MODE_PRIVATE);
        String email1=preferences.getString("email", "");
        email.setText(email1);

        findViewById(R.id.loginSignUp).setOnClickListener(
            (view) ->{
                Intent intentCadastro = new Intent(this, CadastroActivity.class);
                startActivity(intentCadastro);
                Log.d(TAG, "onCreate: Partiu para activity de cadastro");
            }
        );
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}