package com.example.sdjcomp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarYEliminarBicicleta extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private TextView txtCedula, txtFecha, txtLugar, txtMarca, txtnum, txtTipo, txtColor;
    private Button btnModficarBici, btnEliminarBici, btnVolver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        URL="http://"+getResources().getString(R.string.IP)+":3000/getBike/";
        View v = inflater.inflate(R.layout.fragment_modificar_y_eliminar_bicicleta, container, false);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);
        //va al alert dialog del card view
        //((Sesion)getActivity().getApplicationContext()).setIdBici(1);

        txtCedula = (TextView) v.findViewById(R.id.txtCedulaBici);
        txtFecha = (TextView) v.findViewById(R.id.txtFechaRegistroBici);
        txtLugar = (TextView) v.findViewById(R.id.txtLugarRegistroBici);
        txtMarca = (TextView) v.findViewById(R.id.txtMarcaBici);
        txtnum = (TextView) v.findViewById(R.id.txtNumSerieBici);
        txtTipo = (TextView) v.findViewById(R.id.txtTipoBici);
        txtColor = (TextView) v.findViewById(R.id.txtColorBici);
        btnModficarBici = (Button) v.findViewById(R.id.btnModificarBici);
        btnEliminarBici = (Button) v.findViewById(R.id.btnEliminarBici);
        btnVolver = (Button) v.findViewById(R.id.btnVolverModyElim);

        int id = ((Sesion)getActivity().getApplicationContext()).getIdBici();
        System.out.println("id = " + id);

        Call<Bicicleta> call = iRetrofit.executeGetBike(((Sesion)getActivity().getApplicationContext()).getCodigo(), id);
        System.out.println("((Sesion)getActivity().getApplicationContext()).getCodigo() = " + ((Sesion)getActivity().getApplicationContext()).getCodigo());
        call.enqueue(new Callback<Bicicleta>() {
            @Override
            public void onResponse(Call<Bicicleta> call, Response<Bicicleta> response) {
                System.out.println("123");
                txtCedula.setText(response.body().getCedulaPropietario());
                txtFecha.setText(response.body().getFechaRegistro());
                txtLugar.setText(response.body().getLugarRegistro());
                txtMarca.setText(response.body().getMarca());
                txtnum.setText(response.body().getNumSerie());
                txtTipo.setText(response.body().getTipo());
                txtColor.setText(response.body().getColor());
            }

            @Override
            public void onFailure(Call<Bicicleta> call, Throwable t) {
                System.out.println("321");
                System.out.println(t.getMessage());
            }
        });

        btnModficarBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModificarYEliminarBicicleta.this).navigate(R.id.action_modificarYEliminarBicicleta_to_modificarBicicleta);
            }
        });

        btnEliminarBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                URL="http://"+getResources().getString(R.string.IP)+":3000/deleteBicicleta/";
                Call<Number> call = iRetrofit.executeDeleteBicicleta(((Sesion)getActivity().getApplicationContext()).getIdBici());
                call.enqueue(new Callback<Number>() {
                    @Override
                    public void onResponse(Call<Number> call, Response<Number> response) {
                        if(Integer.parseInt(String.valueOf(response.body()))==1){
                            Toast.makeText(getContext(), "Bicicleta eliminada", Toast.LENGTH_LONG).show();
                            NavHostFragment.findNavController(ModificarYEliminarBicicleta.this).navigate(R.id.action_modificarYEliminarBicicleta_to_InterfazEstudiante);
                        }
                    }

                    @Override
                    public void onFailure(Call<Number> call, Throwable t) {
                        Toast.makeText(getContext(), "Bicicleta no eliminada", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModificarYEliminarBicicleta.this).navigate(R.id.action_modificarYEliminarBicicleta_to_InterfazEstudiante);
            }
        });

        return v;
    }
}