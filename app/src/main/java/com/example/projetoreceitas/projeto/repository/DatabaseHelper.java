package com.example.projetoreceitas.projeto.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BancoReceitas";
    private static final Integer DB_VERSION = 4;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //(int id, String name, String email, String password)
        String sqlUser = "CREATE TABLE users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT); ";
        db.execSQL(sqlUser);

        String sqlFavorito = "CREATE TABLE favoritos (favorito_id INTEGER PRIMARY KEY AUTOINCREMENT, receita_id LONG, user_id INTEGER, FOREIGN KEY (user_id) REFERENCES users (user_id));";
        db.execSQL(sqlFavorito);


        /*
        //(int id, String titulo, int rendimento, int tempoPreparo, String categoria)

        String sqlReceita = "create table receitas (receita_id INTEGER PRIMARY KEY, titulo TEXT, rendimento INTEGER, tempo_preparo INTEGER, categoria TEXT);";
        db.execSQL(sqlReceita);

        //(String ingrediente, int receita_id)
        String sqlIngrediente = "CREATE TABLE ingredientes (ingrediente_id INTEGER PRIMARY KEY AUTOINCREMENT, ingrediente TEXT, receita_id INTEGER, FOREIGN KEY (receita_id) REFERENCES receitas (receita_id));";
        db.execSQL(sqlIngrediente);

        //(String passo, int receita_id)
        String sqlModoPreparo = "CREATE TABLE modo_preparo (modo_preparo_id INTEGER PRIMARY KEY AUTOINCREMENT, passo TEXT, receita_id INTEGER, FOREIGN KEY (receita_id) REFERENCES receitas (receita_id));";
        db.execSQL(sqlModoPreparo);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql_upgrade_users = "DROP TABLE IF EXISTS users;";
        db.execSQL(sql_upgrade_users);

        String sql_update_favoritos = "DROP TABLE IF EXISTS favoritos;";
        db.execSQL(sql_update_favoritos);

        onCreate(db);
    }
}

