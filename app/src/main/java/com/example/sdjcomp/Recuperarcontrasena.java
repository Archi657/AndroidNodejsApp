package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sdjcomp.databinding.HomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Recuperarcontrasena extends Fragment {

    private HomeBinding binding;
    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private EditText txtCodigo;
    private EditText txtRespuesta;
    private EditText txtContraseña;
    private Spinner spnPregunta;
    private Button btnCambiarpw, btnVolver;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Recuperarcontrasena() {
        // Required empty public constructor
    }

    public static Recuperarcontrasena newInstance(String param1, String param2) {
        Recuperarcontrasena fragment = new Recuperarcontrasena();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        URL="http://"+getResources().getString(R.string.IP)+":3000/updateUser/";
        View v = inflater.inflate(R.layout.fragment_recuperarcontrasena,container,false);

        txtCodigo = v.findViewById(R.id.txtCodigoR);
        txtRespuesta = v.findViewById(R.id.txtRespuestaR);
        txtContraseña = v.findViewById(R.id.txtContrasenaR);
        btnCambiarpw = v.findViewById(R.id.btnCambiarPw);
        spnPregunta = v.findViewById(R.id.spnPreguntaR);
        btnVolver = v.findViewById(R.id.btnVolverRecuperar);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

        ArrayList<Pregunta> listaPreguntas = new ArrayList<>();
        ArrayList<String> listaPreguntasNom = new ArrayList<>();

        Call<List<Pregunta>> call = iRetrofit.executeGetAll("preguntas");
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        System.out.println("for 1");
                        listaPreguntas.add(response.body().get(i));
                    }
                    for (Pregunta i: listaPreguntas) {
                        System.out.println(i.getPregunta());
                        listaPreguntasNom.add(i.getPregunta());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            listaPreguntasNom);
                    spnPregunta.setAdapter(adapter);
                    System.out.println("tamaño : " + response.body().size());//size de q?
                }// lo unico diferente esq mi spinner se llama pregunta y no preguntas, pero da igual no ?
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                System.out.println("Preguntas No Encontradas");
            }
        });

        btnCambiarpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario objU = new Usuario(txtCodigo.getText().toString(),
                        txtContraseña.getText().toString(), spnPregunta.getSelectedItemPosition()+1,
                        txtRespuesta.getText().toString());

                Call<Number> call = iRetrofit.executeUpdatePassword(objU);
                call.enqueue(new Callback<Number>() {
                    @Override
                    public void onResponse(Call<Number> call, Response<Number> response) {
                        if(response.code()==200){
                            if(Integer.parseInt(String.valueOf(response.body()))==1){
                                Toast.makeText(getContext(), "Contraseña Modificada", Toast.LENGTH_LONG).show();
                                NavHostFragment.findNavController(Recuperarcontrasena.this).navigate(R.id.action_recuperarcontrasena_to_Home);
                            }
                        }
                        if(response.code()==412){
                            Toast.makeText(getContext(), "Respuesta incorrecta", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Number> call, Throwable t) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Recuperarcontrasena.this).navigate(R.id.action_recuperarcontrasena_to_Home);
            }
        });

        return v;
    }
}