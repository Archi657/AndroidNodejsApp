package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AsignarCupo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsignarCupo extends Fragment {

    private EditText edtCodigo;
    private Spinner spnSeccion,spnCupos;
    private Button btnAsignar, btnVolver;

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AsignarCupo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AsignarCupo.
     */
    // TODO: Rename and change types and number of parameters
    public static AsignarCupo newInstance(String param1, String param2) {
        AsignarCupo fragment = new AsignarCupo();
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
        URL="http://"+getResources().getString(R.string.IP)+":3000/createCupo/";
        View v = inflater.inflate(R.layout.asignar_cupo,container,false);
        mostrarCupos(1);
        edtCodigo = v.findViewById(R.id.edtCodigoEnCupos);
        spnSeccion = v.findViewById(R.id.spnSeccionAsignar);
        btnAsignar = v.findViewById(R.id.btnAsignar);
        spnCupos = v.findViewById(R.id.spnCupos);
        btnVolver = v.findViewById(R.id.btnVolverAsignar);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);




        spnSeccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarCupos(spnSeccion.getSelectedItemPosition()+1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAsignar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnCupos.getSelectedItem()!=null){
                    if(!edtCodigo.getText().toString().isEmpty() ){
                        int seccion=spnSeccion.getSelectedItemPosition()+1;
                        int cupo=Integer.parseInt(spnCupos.getSelectedItem().toString());
                        String codigo = edtCodigo.getText().toString();
                        ((Sesion)getActivity().getApplicationContext()).setCupo(cupo);
                        ((Sesion)getActivity().getApplicationContext()).setBicicleta(seccion);
                        ((Sesion)getActivity().getApplicationContext()).setCodio(codigo);
                        Call<List<Bicicleta>> call = iRetrofit.executeGetBikes(((Sesion)getActivity().getApplicationContext()).getCodio());
                        System.out.println("call.isExecuted() = " + call.isExecuted());
                        call.enqueue(new Callback<List<Bicicleta>>() {
                            @Override
                            public void onResponse(Call<List<Bicicleta>> call, Response<List<Bicicleta>> response) {
                                if(response.code()==200 && !response.body().isEmpty()){
                                    Toast.makeText(getContext(), "Eliga la bicicleta que desea registrar", Toast.LENGTH_LONG).show();
                                    NavHostFragment.findNavController(AsignarCupo.this).
                                            navigate(R.id.action_asignarCupo_to_interfazBicicleta);
                                }else{
                                    Toast.makeText(getContext(), "Este estudiante no tiene bicicletas", Toast.LENGTH_LONG).show();
                                    NavHostFragment.findNavController(AsignarCupo.this).
                                            navigate(R.id.action_asignarCupo_to_interfaz_administrador);
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Bicicleta>> call, Throwable t) {
                                Toast.makeText(getContext(), "Este estudiante no tiene bicicletas", Toast.LENGTH_LONG).show();
                                NavHostFragment.findNavController(AsignarCupo.this).
                                        navigate(R.id.action_asignarCupo_to_interfaz_administrador);
                            }
                        });

                    }else{
                        Toast.makeText(getContext(), "Debe rellenar el campo codigo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "No hay cupos disponibles", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AsignarCupo.this).
                        navigate(R.id.action_asignarCupo_to_interfaz_administrador);
            }
        });

        return v;
    }

    public void mostrarCupos(int seccion){
        String URLAUX = "http://"+getResources().getString(R.string.IP)+":3000/getCupo/";
        retrofit = new Retrofit.Builder().baseUrl(URLAUX).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);
        ArrayList<Cupo> listaCupos = new ArrayList<>();
        ArrayList<Number> listaCuposA = new ArrayList<>();
        System.out.println("seccion = " + seccion);
        Call<List<Cupo>> call = iRetrofit.executeGetCuposEnable(String.valueOf(seccion));
        call.enqueue(new Callback<List<Cupo>>() {
            @Override
            public void onResponse(Call<List<Cupo>> call, Response<List<Cupo>> response) {
                if(response.code()==200){
                    for(int i=0; i<response.body().size(); i++){
                        listaCupos.add(response.body().get(i));
                    }
                    for (Cupo i: listaCupos) {
                        listaCuposA.add(i.getIdCupo());
                    }
                    ArrayAdapter<Number> adapter = new ArrayAdapter<Number>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                            listaCuposA);
                    spnCupos.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cupo>> call, Throwable t) {
                System.out.println("Problema en encontrar los cupos");
            }
        });
    }
}