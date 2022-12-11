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
import com.example.projetoreceitas.projeto.model.Usuario;
import com.example.projetoreceitas.projeto.repository.ReceitasRepositorio;
import com.example.projetoreceitas.projeto.repository.UsuarioRepositorio;


public class HomeActivity extends AppCompatActivity {
    private ListaReceitasAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int u = preferences.getInt("usuario_id", 0);
        Usuario usuario = UsuarioRepositorio.getInstance().getUserById(u);
        Log.e(TAG, "onCreate: usuario recebido " + usuario.getName());

        ReceitasRepositorio.getInstance(this, () -> {
            Log.d(TAG, "onCreate: Buscando o banco Json na Home");
            recyclerView = findViewById(R.id.rv_lista_receitas);
            adapter = new ListaReceitasAdapter(getApplicationContext(), ReceitasRepositorio.getInstance().getReceitas());
            recyclerView.setAdapter(adapter);
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            Log.d(TAG, "Tamanho da lista de receitas "+ adapter.getItemCount());

        });
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
                return true;

            case R.id.Favoritos:
                Log.e(TAG, "onOptionsItemSelected: Usuário cliclou em Favoritos");
                Intent intent = new Intent(this, FavoritosActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}