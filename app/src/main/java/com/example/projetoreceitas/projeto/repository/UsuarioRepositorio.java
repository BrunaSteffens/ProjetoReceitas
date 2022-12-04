package com.example.projetoreceitas.projeto.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetoreceitas.projeto.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    //Aqui será feita a integração com a tabela de usuários do banco de dados.
    //Por enquanto será com SQL para poder testar as funcionalidades do app

    private static final String TAG = "UserRepository";
    private static UsuarioRepositorio instance;
    private Context contexto;
    private SQLiteDatabase database;

    public UsuarioRepositorio(Context contexto) {
        super();
        this.contexto = contexto;
        DatabaseHelper dataBaseHelper = new DatabaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    public static UsuarioRepositorio getInstance(Context contexto) {
        if (instance == null) {
            instance = new UsuarioRepositorio(contexto);
        }
        return instance;
    }

    public static UsuarioRepositorio getInstance() {
        return instance;
    }

    public void addUser(Usuario usuario) {
        String sql = "insert into users (name, email, password) values(?, ?, ?);";
        Object[] args = {usuario.getName(), usuario.getEmail(), usuario.getPassword()};
        database.execSQL(sql, args);
    }

    public void updateUser(Usuario usuario){
        String sql = "update users set name = ?, email = ?, password = ?;";
        Object[] args = {usuario.getName(), usuario.getEmail(), usuario.getPassword()};
        database.execSQL(sql);
    }

    public void deleteUser(Usuario usuario){
        String sql = "delete from users where email = ?;";
        Object[] args = { usuario.getEmail()};
        database.execSQL(sql);
    }

    public List<Usuario> getUsers() {
        String sql_getUsers = "SELECT * FROM users;";
        Cursor cursor = database.rawQuery(sql_getUsers, null);
        cursor.moveToFirst();
        List<Usuario> usuarioList = new ArrayList<>();

        do{
            usuarioList.add(userFromCursor(cursor));
        }while(cursor.moveToNext());
        return usuarioList;
    }

    public void addUserTest(){
        try {
            String sql = "insert into users (name, email, password) values ('Bruna', 'bruna@', '1234');";
            database.execSQL(sql);
            Log.d(TAG, "addUserTest: gravação de usuário teste");
        } catch (Exception e){
            Log.d(TAG, "addUserTest: usuário já existe");
        }
    }

    public Usuario getUserByEmail(String email) {
        String sql_getUserByEmail = "SELECT * FROM users WHERE email=?;";
        String [] args = {email};
        Cursor cursor = database.rawQuery(sql_getUserByEmail, args );

        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    public Usuario getUserById(int id) {
        String sql_getUserById = "SELECT * FROM users WHERE user_id=?;";
        String [] args = {""+id};
        Cursor cursor = database.rawQuery(sql_getUserById, args );

        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    private Usuario userFromCursor(Cursor cursor) {
        //int id, String name, String email, String password
        Usuario usuario = new Usuario(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return usuario;
    }


}
