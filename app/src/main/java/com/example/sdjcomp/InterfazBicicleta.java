package com.example.sdjcomp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InterfazBicicleta extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private RecyclerView recyclerViewCiclas;
    private RecyclerViewAdapter adapterCiclas;
    AlertDialog.Builder alertBici;

    private Button btnCerrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        URL="http://"+getResources().getString(R.string.IP)+":3000/getBikes/";
        View v = inflater.inflate(R.layout.fragment_interfaz_bicicleta, container, false);

        //btnVer = (Button) v.findViewById(R.id.btnCargar);
        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

        btnCerrar = v.findViewById(R.id.btnCerrarBicicletas);

        alertBici = new AlertDialog.Builder(getActivity());
        //cardView = (CardView) v.findViewById(R.id.cardView);

        List<Bicicleta> lstBicicletas = new ArrayList<>();
        System.out.println("((Sesion)getActivity().getApplicationContext()).getCodigo() = " + ((Sesion)getActivity().getApplicationContext()).getCodigo());
        Call<List<Bicicleta>> call = null;
        if(((Sesion)getActivity().getApplicationContext()).getRol_id()==1){
            call=iRetrofit.executeGetBikes(((Sesion)getActivity().getApplicationContext()).getCodio());
        }else if(((Sesion)getActivity().getApplicationContext()).getRol_id()==2){
            call=iRetrofit.executeGetBikes(((Sesion)getActivity().getApplicationContext()).getCodigo());
        }

        call.enqueue(new Callback<List<Bicicleta>>() {
            @Override
            public void onResponse(Call<List<Bicicleta>> call, Response<List<Bicicleta>> response) {
                if(response.code()==200)
                {
                    System.out.println(response.body().size());
                    for(int i=0; i<response.body().size();i++)
                    {
                        lstBicicletas.add(response.body().get(i));
                    }
                    recyclerViewCiclas=(RecyclerView)v.findViewById(R.id.recBicicleta);
                    recyclerViewCiclas.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapterCiclas=new RecyclerViewAdapter(lstBicicletas);

                    adapterCiclas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            System.out.println("Averrer");
                            System.out.println(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getMarca());
                            if(((Sesion)getActivity().getApplicationContext()).getRol_id()==1){
                                alertBici.setMessage("Marca: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getMarca()+
                                                "\nTipo: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getTipo()+
                                                "\nId: "+String.valueOf(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getIdBicicleta())+
                                                "\nColor: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getColor())
                                        .setCancelable(true).setTitle("Tu Bicicleta")
                                        .setPositiveButton("Asignar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                String palabras =String.valueOf(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getIdBicicleta())
                                                        +","+((Sesion)getActivity().getApplicationContext()).getCupo();
                                                Call<Parqueadero> callParqueadero = iRetrofit.executeCreateParqueadero(palabras);
                                                callParqueadero.enqueue(new Callback<Parqueadero>() {
                                                    @Override
                                                    public void onResponse(Call<Parqueadero> call, Response<Parqueadero> response) {
                                                        if(response.code()==200){
                                                            Toast.makeText(getContext(), "Bicicleta Asignada al cupo", Toast.LENGTH_LONG).show();
                                                            NavHostFragment.findNavController(InterfazBicicleta.this)
                                                                    .navigate(R.id.action_interfazBicicleta_to_interfaz_administrador);
                                                        }else if(response.code()==412){
                                                            Toast.makeText(getContext(), "La Bicicleta ya tiene un cupo asignado", Toast.LENGTH_LONG).show();
                                                            NavHostFragment.findNavController(InterfazBicicleta.this)
                                                                    .navigate(R.id.action_interfazBicicleta_to_interfaz_administrador);
                                                        }
                                                    }
                                                    @Override
                                                    public void onFailure(Call<Parqueadero> call, Throwable t) {
                                                        Toast.makeText(getContext(), "La Bicicleta no se pudo asignar", Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                            }
                                        })
                                        .create().show();
                            }else if(((Sesion)getActivity().getApplicationContext()).getRol_id()==2){
                                alertBici.setMessage("Marca: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getMarca()+
                                                "\nTipo: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getTipo()+
                                                "\nId: "+String.valueOf(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getIdBicicleta())+
                                                "\nColor: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getColor())
                                        .setCancelable(true).setTitle("Tu Bicicleta")
                                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                NavHostFragment.findNavController(InterfazBicicleta.this)
                                                        .navigate(R.id.action_interfazBicicleta_self);
                                            }
                                        }).setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                ((Sesion)getActivity().getApplicationContext()).setIdBici(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getIdBicicleta());
                                                NavHostFragment.findNavController(InterfazBicicleta.this)
                                                        .navigate(R.id.action_interfazBicicleta_to_modificarYEliminarBicicleta);
                                            }
                                        })
                                        .create().show();
                            }
                        }
                    });
                    recyclerViewCiclas.setAdapter(adapterCiclas);
                }
            }

            @Override
            public void onFailure(Call<List<Bicicleta>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Fail");
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((Sesion)getActivity().getApplicationContext()).getRol_id()==1){
                    NavHostFragment.findNavController(InterfazBicicleta.this)
                            .navigate(R.id.action_interfazBicicleta_to_interfaz_administrador);
                }else if(((Sesion)getActivity().getApplicationContext()).getRol_id()==2){
                    NavHostFragment.findNavController(InterfazBicicleta.this)
                            .navigate(R.id.action_interfazBicicleta_to_InterfazEstudiante);
                }
            }
        });

        return v;
    }
}