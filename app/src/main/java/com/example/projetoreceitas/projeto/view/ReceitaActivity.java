package com.example.projetoreceitas.projeto.view;

import static android.content.ContentValues.TAG;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Favorito;
import com.example.projetoreceitas.projeto.model.Receita;
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.repository.FavoritoRepositorio;
import com.example.projetoreceitas.projeto.repository.ReceitasRepositorio;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;

public class ReceitaActivity extends AppCompatActivity {
    private Usuario usuario = new Usuario();
    Receita receita = new Receita();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        long b = getIntent().getLongExtra("receita_id", 0);
        receita = ReceitasRepositorio.getInstance().getReceitaById(b);
        Log.e(TAG, "onCreate: localizada a receita " + receita.getReceita_id());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int u = preferences.getInt("usuario_id", 0);
        usuario = UsuarioRepositorio.getInstance().getUserById(u);
        Log.e(TAG, "onCreate: usuario recebido " + usuario.getName());


        ((TextView) findViewById(R.id.receitaTitulo)).setText(receita.getTitulo());
        ((TextView) findViewById(R.id.receitaRendimento)).setText(receita.getRendimento());
        ((TextView) findViewById(R.id.receitaIngredientes)).setText(receita.getIngredientes());
        ((TextView) findViewById(R.id.receitaModoPreparo)).setText(receita.getModo_preparo());

        findViewById(R.id.receita_favoritar).setOnClickListener(
                v -> {
                    Log.e(TAG, "onClick: Usuario clicou no botão favoritar");

                    Log.e(TAG, "onCreate: Recebido o usuário " + usuario.getName() + " id " + usuario.getId());

                    Favorito favorito = new Favorito( receita.getReceita_id(), usuario.getId());
                    Log.e(TAG, "onClick: Criado o favorito user id " + favorito.getUser_id() + " id receita " + favorito.getReceita_id() );

                    FavoritoRepositorio.getInstance(getApplicationContext()).addFavorito(favorito);
                    Log.e(TAG, "onClick: favorito adicionado para o usuário" + favorito.getUser_id() + "da receita " + favorito.getReceita_id());

                    Toast.makeText(getApplicationContext(), "Favorito adicionado!", Toast.LENGTH_SHORT).show();

                });

    }
}