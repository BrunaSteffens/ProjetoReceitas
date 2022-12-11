package com.example.projetoreceitas.projeto.view;

import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.adapter.ListaReceitasAdapter;
import com.example.projetoreceitas.projeto.model.Receita;
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.repository.FavoritoRepositorio;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {
    private ListaReceitasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int u = preferences.getInt("usuario_id", 0);
        Usuario usuario = UsuarioRepositorio.getInstance().getUserById(u);
        Log.e(TAG, "onCreate Favoritos: usuario recebido " + usuario.getName());

        List<Receita> receitasFavoritas = FavoritoRepositorio.getInstance(this).getFavoritos(usuario.getId());
        Log.e(TAG, "onCreate Favoritos activity: "+ receitasFavoritas.size() );

        RecyclerView recyclerView = findViewById(R.id.rv_lista_receitas);
        adapter = new ListaReceitasAdapter(this, receitasFavoritas);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

        Log.d(TAG, "Tamanho da lista de receitas "+ adapter.getItemCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem pesquisar = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) pesquisar.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String titulo) {
                adapter.getFilter().filter(titulo);
                return false;
            }
        });
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.Home:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;

            case R.id.Favoritos:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}