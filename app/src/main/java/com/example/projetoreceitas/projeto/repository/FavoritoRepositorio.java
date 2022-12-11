package com.example.projetoreceitas.projeto.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.projetoreceitas.projeto.model.Favorito;
import com.example.projetoreceitas.projeto.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class FavoritoRepositorio {
    private static final String TAG = "FavoritoRepositorio";
    private static FavoritoRepositorio instance;
    private Context contexto;
    private SQLiteDatabase database;

    public FavoritoRepositorio(Context contexto){
        super();
        this.contexto = contexto;
        DatabaseHelper dataBaseHelper = new DatabaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static FavoritoRepositorio getInstance(Context contexto) {
        if (instance == null) {
            instance = new FavoritoRepositorio(contexto);
        }
        return instance;
    }

    public static FavoritoRepositorio getInstance() {
        return instance;
    }

    public void addFavorito(Favorito favorito) {
        String sql = "INSERT INTO favoritos (receita_id, user_id) values (?, ?);";
        Object[] args = {favorito.getReceita_id(), favorito.getUser_id() };

        Receita r = ReceitasRepositorio.getInstance().getReceitaById(favorito.getReceita_id());
        List<Receita> favoritos = getFavoritos(favorito.getUser_id());
        if (!favoritos.contains(r)){
            database.execSQL(sql, args);
            Toast.makeText(contexto, "Favorito adicionado!", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(contexto, "Favorito já está adicionado!", Toast.LENGTH_SHORT).show();
        }

    }

    public List<Receita> getFavoritos(int id){
        List<Favorito> favoritos = new ArrayList<>();
        List<Receita> receitasFavoritas = new ArrayList<>();

        String sql_getFavoritos = "SELECT * FROM favoritos WHERE user_id = ?;";
        String[] args = {""+id};

        Cursor cursor = database.rawQuery(sql_getFavoritos, args );
        cursor.moveToFirst();

        if(cursor != null && cursor.moveToFirst()) {
            do {
                favoritos.add(favoritoFromCursor(cursor));
            } while (cursor.moveToNext());
        }

        Log.e(TAG, "getFavoritos: tamanho da lista: "+ favoritos.size());

        List<Receita> receitaList = ReceitasRepositorio.getInstance().getReceitas();
        for(Receita r:receitaList){
            for(Favorito f: favoritos){
                if (r.getReceita_id() == f.getReceita_id()){
                    receitasFavoritas.add(r);
                }
            }
        }
        Log.e(TAG, "getFavoritos: tamanho da lista de receitas: " + receitasFavoritas.size() );
        return receitasFavoritas;
    }

    private Favorito favoritoFromCursor(Cursor cursor){
        //int favorito_id, int user_id, int receita_id
        Favorito favorito = new Favorito(
                cursor.getInt(0),
                cursor.getLong(1),
                cursor.getInt(2));
        return favorito;
    }
}
