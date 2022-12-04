package com.example.projetoreceitas.projeto.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "BancoReceitas";
    private static final Integer DB_VERSION = 1;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //(int id, String name, String email, String password)
        String sqlUser = "create table users (user_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT); ";
        db.execSQL(sqlUser);


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

        String sql_upgrade_users = "DROP TABLE IF EXISTS users";
        db.execSQL(sql_upgrade_users);

        /*
        String sql_upgrade_receitas = "DROP TABLE IF EXISTS receitas";
        db.execSQL(sql_upgrade_receitas);

        String sql_upgrade_ingredientes = "DROP TABLE IF EXISTS ingredientes";
        db.execSQL(sql_upgrade_ingredientes);

        String sql_upgrade_modo_preparo = "DROP TABLE IF EXISTS modo_preparo";
        db.execSQL(sql_upgrade_modo_preparo);

         */

        onCreate(db);
    }
}

