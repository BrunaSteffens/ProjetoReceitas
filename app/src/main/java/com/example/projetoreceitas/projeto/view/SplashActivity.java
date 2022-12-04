package com.example.projetoreceitas.projeto.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.repository.ReceitasRepositorio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        }, 5000);
    }
}