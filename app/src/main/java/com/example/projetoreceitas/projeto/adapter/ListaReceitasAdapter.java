package com.example.projetoreceitas.projeto.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoreceitas.R;
import com.example.projetoreceitas.projeto.model.Receita;
import com.example.projetoreceitas.projeto.view.HomeActivity;
import com.example.projetoreceitas.projeto.view.ReceitaActivity;

import java.util.ArrayList;
import java.util.List;

public class ListaReceitasAdapter extends RecyclerView.Adapter {
    private List<Receita> receitaList;
    private Context context;
    private static final String TAG = "ListaReceitasAdapter";
    public View layoutVH;

    public ListaReceitasAdapter(List<Receita> receitaList) {
        this.receitaList = receitaList;
    }

    public ListaReceitasAdapter() {
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_receitas, parent, false);
        return new ListaReceitasViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Receita receita = receitaList.get(position);

        ((TextView) holder.itemView.findViewById(R.id.nome_receita)).setText(receita.getTitulo());
        ((TextView) holder.itemView.findViewById(R.id.rendimento)).setText(receita.getRendimento());

        holder.itemView.findViewById(R.id.nome_receita).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReceitaActivity.class);
                intent.putExtra("receita_id", receita.getReceita_id());
                Log.d(TAG, "onClick: Repassando receita: "+receita.getReceita_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return receitaList.size();
    }
}

class ListaReceitasViewHolder extends RecyclerView.ViewHolder {
    public View view;

    public ListaReceitasViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

    }
}
