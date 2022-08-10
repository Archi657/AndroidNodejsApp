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
import android.widget.EditText;
import android.widget.TextView;
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
 * Use the {@link ConsultarParqueaderoSeccion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarParqueaderoSeccion extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private RecyclerView recyclerViewCiclas;
    private RecyclerViewAdapter adapterCiclas;
    AlertDialog.Builder alertBici;

    private Button btnVolver;
    private TextView txtCantidad;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConsultarParqueaderoSeccion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarParqueaderoSeccion.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarParqueaderoSeccion newInstance(String param1, String param2) {
        ConsultarParqueaderoSeccion fragment = new ConsultarParqueaderoSeccion();
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
        URL="http://"+getResources().getString(R.string.IP)+":3000/getParqueadero/";
        View v = inflater.inflate(R.layout.consultar_parqueadero_seccion,container,false);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

        alertBici = new AlertDialog.Builder(getActivity());

        btnVolver = v.findViewById(R.id.btnCerrarConsultaParqueaderos);
        txtCantidad = v.findViewById(R.id.txtCantidadCiclas);

        List<Bicicleta> lstBicicletas = new ArrayList<>();

        Call<List<Bicicleta>> call=iRetrofit.executeGetParqueaderos(((Sesion)getActivity().getApplicationContext()).getSeccion());
        System.out.println("call.isExecuted() = " + call.isExecuted());
        call.enqueue(new Callback<List<Bicicleta>>() {
            @Override
            public void onResponse(Call<List<Bicicleta>> call, Response<List<Bicicleta>> response) {
                if(response.code()==200)
                {
                    System.out.println("Hasta aqui bien");
                    System.out.println(response.body().size());
                    for(int i=0; i<response.body().size();i++)
                    {
                        lstBicicletas.add(response.body().get(i));
                    }
                    recyclerViewCiclas=(RecyclerView)v.findViewById(R.id.recBicicletaAdmin);
                    recyclerViewCiclas.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapterCiclas=new RecyclerViewAdapter(lstBicicletas);


                    adapterCiclas.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            System.out.println("Averrer");
                            System.out.println(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getMarca());
                            alertBici.setMessage("Marca: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getMarca()+
                                            "\nTipo: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getTipo()+
                                            "\nId: "+String.valueOf(lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getIdBicicleta())+
                                            "\nColor: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getColor()+
                                            "\nNumSerie: "+lstBicicletas.get(recyclerViewCiclas.getChildAdapterPosition(view)).getNumSerie())
                                    .setCancelable(true).setTitle("Tu Bicicleta")
                                    .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            NavHostFragment.findNavController(ConsultarParqueaderoSeccion.this)
                                                    .navigate(R.id.action_consultarParqueaderoSeccion_self);
                                        }
                                    })
                                    .create().show();
                        }
                    });
                    recyclerViewCiclas.setAdapter(adapterCiclas);
                    txtCantidad.setText("Numero de ciclas:"+response.body().size());
                }
            }

            @Override
            public void onFailure(Call<List<Bicicleta>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Fail");
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ConsultarParqueaderoSeccion.this)
                        .navigate(R.id.action_consultarParqueaderoSeccion_to_consultarCupo);
            }
        });

        return v;
    }
}