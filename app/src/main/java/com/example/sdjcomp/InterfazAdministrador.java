package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InterfazAdministrador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InterfazAdministrador extends Fragment {

    private Button btnVerRegistrosCupos;
    private Button btnCerrarSesion;
    private Button btnAsignarCupo;
    private Button btnDeAsignarCupo;

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

    public InterfazAdministrador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InterfazAdministrador.
     */
    // TODO: Rename and change types and number of parameters
    public static InterfazAdministrador newInstance(String param1, String param2) {
        InterfazAdministrador fragment = new InterfazAdministrador();
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
        View v = inflater.inflate(R.layout.interfaz_administrador,container,false);
        URL="http://"+getResources().getString(R.string.IP)+":3000/getUser/";
        btnCerrarSesion = v.findViewById(R.id.btnCerrarSesion);
        btnVerRegistrosCupos = v.findViewById(R.id.btnVerRegistrosCupos);
        btnAsignarCupo = v.findViewById(R.id.btnAsignarCupo);
        btnDeAsignarCupo = v.findViewById(R.id.btnDesAsignarCupo);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

        Call<Usuario> call = iRetrofit.executeGetUser(((Sesion)getActivity().getApplicationContext()).getCorreo());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.code()==200){
                    ((Sesion)getActivity().getApplicationContext()).setCodigo(response.body().getCodigo());
                    ((Sesion)getActivity().getApplicationContext()).setNombre(response.body().getNombre());
                    ((Sesion)getActivity().getApplicationContext()).setCorreo(response.body().getCorreo());
                    ((Sesion)getActivity().getApplicationContext()).setClave(response.body().getClave());
                    ((Sesion)getActivity().getApplicationContext()).setPseguridad(response.body().getPseguridad());
                    ((Sesion)getActivity().getApplicationContext()).setRseguridad(response.body().getRseguridad());
                    ((Sesion)getActivity().getApplicationContext()).setRol_id(response.body().getRol_id());

                }

            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnVerRegistrosCupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InterfazAdministrador.this)
                        .navigate(R.id.action_interfaz_administrador_to_consultarCupo);
            }
        });

        btnAsignarCupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InterfazAdministrador.this)
                        .navigate(R.id.action_interfaz_administrador_to_asignarCupo);
            }
        });

        btnDeAsignarCupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(InterfazAdministrador.this)
                        .navigate(R.id.action_interfaz_administrador_to_desasignarCupo);
            }
        });

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Sesion) getActivity().getApplicationContext()).setCorreo("");
                NavHostFragment.findNavController(InterfazAdministrador.this)
                        .navigate(R.id.action_interfaz_administrador_to_Home);
            }
        });

        return v;
    }
}