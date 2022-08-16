package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class admPreguntas extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private TableLayout tablaPreguntas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        URL="http://"+getResources().getString(R.string.IP)+":3000/getAll/preguntas/";
        View v = inflater.inflate(R.layout.fragment_adm_preguntas,container,false);
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);
        tablaPreguntas = v.findViewById(R.id.tablaPreguntas);
        Call<List<Pregunta>> call = iRetrofit.executeGetAll("preguntas");

        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                for(int i=0; i<response.body().size(); i++){
                    TableRow fila = new TableRow(getActivity());
                    TextView textId = new TextView(getActivity());
                    TextView textPregunta = new TextView(getActivity());
                    textId.setText(String.valueOf(response.body().get(i).getCodigo()));
                    textPregunta.setText(response.body().get(i).getPregunta());
                    fila.addView(textId);
                    fila.addView(textPregunta);
                    tablaPreguntas.addView(fila);
                }
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                Snackbar.make(v, "No se pudieron encontrar las preguntas", Snackbar.LENGTH_LONG).show();
            }
        });

        return v;
    }
}