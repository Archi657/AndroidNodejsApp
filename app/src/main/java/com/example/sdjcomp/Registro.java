package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="http://192.168.1.14:3000/register/";

    private EditText edtCodigo;
    private EditText edtNombre;
    private EditText edtCorreo;
    private EditText edtClave;
    private EditText edtRespuesta;
    private Spinner spnPreguntas;
    private Button btnRegistrar;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
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
        View v = inflater.inflate(R.layout.fragment_registro, container, false);
        edtCodigo = (EditText) v.findViewById(R.id.edtCodigo);
        edtNombre = (EditText) v.findViewById(R.id.edtNombre);
        edtCorreo = (EditText) v.findViewById(R.id.edtCorreo);
        edtClave = (EditText) v.findViewById(R.id.edtClave);
        edtRespuesta = (EditText) v.findViewById(R.id.edtRespuesta);
        btnRegistrar = (Button) v.findViewById(R.id.btnRegistrarUsuario);
        spnPreguntas = (Spinner) v.findViewById(R.id.spnPreguntas);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

          int Pregunta = 1;
        switch (spnPreguntas.getSelectedItemPosition()){
            case 1:{
                Pregunta = 1;
                break;
            }
            case 2:{
                Pregunta = 2;
                break;
            }
            case 3:{
                Pregunta = 3;
                break;
            }
        }

        final int preguntaF = Pregunta;
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario objU = new Usuario(edtCodigo.getText().toString(),edtNombre.getText().toString(),
                        edtCorreo.getText().toString(),edtClave.getText().toString(),
                        preguntaF,edtRespuesta.getText().toString(),2);

                HashMap<String,Usuario> map = new HashMap<>();

                map.put("usuario",objU);
                System.out.println("objU PS = " + objU.getPseguridad());
                System.out.println("objU RS = " + objU.getRseguridad());
                System.out.println("objU rol = " + objU.getRol_id());

                Call<Number> call = iRetrofit.executeRegister(objU);
                call.enqueue(new Callback<Number>() {
                    @Override
                    public void onResponse(Call<Number> call, Response<Number> response) {
                        if(response.code()==200){
                            if(Integer.parseInt(String.valueOf(response.body()))==1){
                                Toast.makeText(getContext(), "Usuario Registrado Con Exito", Toast.LENGTH_LONG).show();
                                NavHostFragment.findNavController(Registro.this).navigate(R.id.action_fragment_registro_to_Home);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Number> call, Throwable t) {
                        System.out.println("t = " + t);
                        Toast.makeText(getContext(), "Usuario No Registrado", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return v;
    }
}