package com.example.projetoreceitas.projeto.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Receita implements Parcelable {
    private long receita_id;
    private String titulo, ingredientes, modo_preparo, rendimento;

    public Receita(long receita_id, String titulo, String ingredientes, String modo_preparo, String rendimento) {
        this.receita_id = receita_id;
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.modo_preparo = modo_preparo;
        this.rendimento = rendimento;
    }

    public Receita(long receita_id, String titulo) {
        this.receita_id = receita_id;
        this.titulo = titulo;
    }

    public Receita() {
    }

    protected Receita(Parcel in){
        this.receita_id = in.readInt();
        this.titulo = in.readString();
        this.ingredientes = in.readString();
        this.modo_preparo = in.readString();
        this.rendimento = in.readString();
    }


    public static final Creator<Receita> CREATOR = new Creator<Receita>() {
        @Override
        public Receita createFromParcel(Parcel in) {
            return new Receita(in);
        }

        @Override
        public Receita[] newArray(int size) {
            return new Receita[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(receita_id);
        parcel.writeString(titulo);
        parcel.writeString(ingredientes);
        parcel.writeString(modo_preparo);
        parcel.writeString(rendimento);
    }



    public long getReceita_id() {
        return receita_id;
    }

    public void setReceita_id(long receita_id) {
        this.receita_id = receita_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getModo_preparo() {
        return modo_preparo;
    }

    public void setModo_preparo(String modo_preparo) {
        this.modo_preparo = modo_preparo;
    }

    public String getRendimento() {
        return rendimento;
    }

    public void setRendimento(String rendimento) {
        this.rendimento = rendimento;
    }

}
