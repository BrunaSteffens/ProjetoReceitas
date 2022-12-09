package com.example.projetoreceitas.projeto.repository;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetoreceitas.projeto.model.Favorito;
import com.example.projetoreceitas.projeto.model.Receita;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ReceitasRepositorio implements ReceitaRepositorioInterface, Response.Listener<JSONArray>, Response.ErrorListener {
    private static final String TAG = "ReceitasRepositorio";
    private final List<Receita> receitaList ;
    private static ReceitasRepositorio instance;
    private Context context;
    private RequestQueue requestQueue;

    private OnReadyListener onReadyListener;

    private ReceitasRepositorio(Context context) {
        super();

        this.context = context;
        receitaList = new ArrayList<>();
        this.requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "https://raw.githubusercontent.com/BrunaSteffens/ReceitasDeCasa/master/Receitas.json", null, this, this);

        requestQueue.add(jsonArrayRequest);
        Log.e(TAG, "ReceitasRepositorio: repositório lançado");

    }

    public static ReceitasRepositorio getInstance(){
        return instance;
    }

    public static ReceitasRepositorio getInstance(Context context, OnReadyListener orl){
        if (instance== null){
            instance = new ReceitasRepositorio(context);
            instance.onReadyListener = orl;
        }
        if (!instance.getReceitas().isEmpty()){
            if (orl != null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

    @Override
    public List<Receita> getReceitas(){ return this.receitaList; }


    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e(TAG, "onErrorResponse: "+error.getMessage() );
    }

    @Override
    public void onResponse(JSONArray response) {
        Log.e(TAG, "onResponse: " + response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject jsonObject = response.getJSONObject(i);
                Log.d(TAG, "onResponse: " + jsonObject.toString());
                //long receita_id, String titulo, String ingredientes, String modo_preparo, String rendimento
                receitaList.add(new Receita(
                        jsonObject.getLong("id"),
                        jsonObject.getString("nome"),
                        jsonObject.getString("ingredientes"),
                        jsonObject.getString("modo_preparo"),
                        jsonObject.getString("Rendimento")));

            } catch (JSONException e) {
                Log.e(TAG, "onResponse: " + e.getMessage());
                e.printStackTrace();
            }
        }
        if (onReadyListener !=null){
            onReadyListener.onReady();
        }
        onReadyListener=null;
        Log.e(TAG, "onResponse: finalizado");

    }


    @Override
    public Receita getReceitaById(long id) {
        Receita receita = null;
        for (Receita r: receitaList){
            if (r.getReceita_id() == id){
                receita = r;
            }
        }
        return receita;
    }


    @Override
    public Receita getReceitabyNome(String nome) {
        return null;
    }
}