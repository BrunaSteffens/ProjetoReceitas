package com.example.projetoreceitas.projeto.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoreceitas.projeto.model.Usuario;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private List<Usuario> usuarioList;
    private static final String TAG = "UserAdapter";

    public UserAdapter(List<Usuario> usuarioList){
        this.usuarioList = usuarioList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder{
    public View view;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}
