package com.example.projetoreceitas.projeto.view;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Receita;
import com.example.projetoreceitas.projeto.repository.ReceitasRepositorio;

public class ReceitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);
        Receita receita = new Receita();

        Bundle b = getIntent().getExtras();
        long id = b.getLong("receita_id");
        Log.d(TAG, "onCreate: Recebida a receita id: "+id);
        receita = ReceitasRepositorio.getInstance().getReceitaById(id);
        Log.d(TAG, "onCreate: localizada a receita" + receita);

        ((TextView) findViewById(R.id.receitaTitulo)).setText(receita.getTitulo());
        ((TextView) findViewById(R.id.receitaRendimento)).setText(receita.getRendimento());
        ((TextView) findViewById(R.id.receitaIngredientes)).setText(receita.getIngredientes());
        ((TextView) findViewById(R.id.receitaModoPreparo)).setText(receita.getModo_preparo());


    }
}