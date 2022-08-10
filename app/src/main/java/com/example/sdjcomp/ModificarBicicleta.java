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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModificarBicicleta extends Fragment {

    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="";
    private EditText txtCedula, txtFecha, txtLugar, txtnum, txtColor;
    private Spinner spnMarca, spnTipo;
    private Button btnModficarBici, btnVolver;
    private String auxMarca, auxTipo, auxCodigo;

    public ModificarBicicleta() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        URL = "http://" + getResources().getString(R.string.IP) + ":3000/getBike/";
        View v = inflater.inflate(R.layout.fragment_modificar_bicicleta, container, false);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);
        //va al alert dialog del card view
        //((Sesion) getActivity().getApplicationContext()).setIdBici(1);

        txtCedula = (EditText) v.findViewById(R.id.txtCedulaBici1);
        txtFecha = (EditText) v.findViewById(R.id.txtFechaRegistroBici1);
        txtLugar = (EditText) v.findViewById(R.id.txtLugarRegistroBici1);
        spnMarca = (Spinner) v.findViewById(R.id.spnMarcaBici1);
        txtnum = (EditText) v.findViewById(R.id.txtNumSerieBici1);
        spnTipo = (Spinner) v.findViewById(R.id.spnTipoBici1);
        txtColor = (EditText) v.findViewById(R.id.txtColorBici1);
        btnModficarBici = (Button) v.findViewById(R.id.btnModificarBici1);
        btnVolver = (Button) v.findViewById(R.id.btnVolverModBici);

        int id = ((Sesion) getActivity().getApplicationContext()).getIdBici();
        System.out.println("id = " + id);

        Call<Bicicleta> call = iRetrofit.executeGetBike(((Sesion) getActivity().getApplicationContext()).getCodigo(), id);
        System.out.println("((Sesion)getActivity().getApplicationContext()).getCodigo() = " + ((Sesion) getActivity().getApplicationContext()).getCodigo());
        call.enqueue(new Callback<Bicicleta>() {
            @Override
            public void onResponse(Call<Bicicleta> call, Response<Bicicleta> response) {
                if(response.code()==200)
                {
                    System.out.println("xd" + response.body().getCedulaPropietario());
                    txtCedula.setText(response.body().getCedulaPropietario());
                    txtFecha.setText(response.body().getFechaRegistro());
                    txtLugar.setText(response.body().getLugarRegistro());
                    auxMarca = (response.body().getMarca());
                    txtnum.setText(response.body().getNumSerie());
                    auxTipo = (response.body().getTipo());
                    txtColor.setText(response.body().getColor());
                    auxCodigo = response.body().getEstudiante_id();
                }

            }

            @Override
            public void onFailure(Call<Bicicleta> call, Throwable t) {
                System.out.println("321");
                System.out.println(t.getMessage());
            }
        });

        //Marcas

        //rellenar spinner marcas

        ArrayList<Marca> lstMarcas = new ArrayList<>();
        ArrayList<String> auxLstMarcas = new ArrayList<>();

        Call<List<Marca>> call1 = iRetrofit.executeGetMarca();
        call1.enqueue(new Callback<List<Marca>>() {
            @Override
            public void onResponse(Call<List<Marca>> call, Response<List<Marca>> response) {
                if(response.code()==200)
                {
                    for(int i=0; i<response.body().size(); i++)
                    {
                        lstMarcas.add(response.body().get(i));
                    }
                    for(Marca m: lstMarcas)
                    {
                        auxLstMarcas.add(m.getMarca());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, auxLstMarcas);
                    spnMarca.setAdapter(adapter);
                    for(int i=0; i<lstMarcas.size();i++)
                    {
                        if(lstMarcas.get(i).getMarca().equals(auxMarca))
                        {
                            spnMarca.setSelection(i);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Marca>> call, Throwable t) {
                System.out.println("Marcas No Encontradas");
            }
        });

        //rellenar spinner marcas

        ArrayList<Tipo> lstTipos = new ArrayList<>();
        ArrayList<String> auxLstTipos = new ArrayList<>();

        Call<List<Tipo>> call2 = iRetrofit.executeGetTipos();
        call2.enqueue(new Callback<List<Tipo>>() {
            @Override
            public void onResponse(Call<List<Tipo>> call, Response<List<Tipo>> response) {
                if(response.code()==200)
                {
                    for(int i=0; i<response.body().size(); i++)
                    {
                        lstTipos.add(response.body().get(i));
                    }
                    for(Tipo m: lstTipos)
                    {
                        auxLstTipos.add(m.getTipo());
                    }
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, auxLstTipos);
                    spnTipo.setAdapter(adapter2);
                    for(int i=0; i<lstTipos.size();i++)
                    {
                        if(lstTipos.get(i).getTipo().equals(auxTipo))
                        {
                            spnTipo.setSelection(i);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Tipo>> call, Throwable t) {
                System.out.println("Tipos No Encontradas");
            }
        });

        //boton
        btnModficarBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BicicletaRegistrar objB = new BicicletaRegistrar();
                objB.setEstudiante_id(auxCodigo);
                objB.setFechaRegistro(txtFecha.getText().toString());
                objB.setLugarRegistro(txtLugar.getText().toString());
                objB.setNumSerie(txtnum.getText().toString());
                objB.setMarca(spnMarca.getSelectedItemPosition()+1);
                objB.setTipo(spnTipo.getSelectedItemPosition()+1);
                objB.setColor(txtColor.getText().toString());
                objB.setIdBicicleta(id);
                objB.setCedulaPropietario(txtCedula.getText().toString());

                if(!txtCedula.getText().toString().isEmpty()
                        && !txtFecha.getText().toString().isEmpty()
                        && !txtLugar.getText().toString().isEmpty()
                        && !txtnum.getText().toString().isEmpty()
                        && !txtColor.getText().toString().isEmpty())
                {
                    Call<Number> call = iRetrofit.executeUpdateBicicleta(objB);
                    call.enqueue(new Callback<Number>() {
                        @Override
                        public void onResponse(Call<Number> call, Response<Number> response) {
                            if(response.code()==200){
                                if(Integer.parseInt(String.valueOf(response.body()))==1){
                                    Toast.makeText(getContext(), "Bicicleta actualizada", Toast.LENGTH_LONG).show();
                                    NavHostFragment.findNavController(ModificarBicicleta.this).navigate(R.id.action_modificarBicicleta_to_modificarYEliminarBicicleta);
                                }
                            }else if(response.code()==412){
                                Snackbar.make(v, "Este numero de serie ya esta registrado", Snackbar.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Number> call, Throwable t) {
                            Toast.makeText(getContext(), "Bicicleta no actualizada", Toast.LENGTH_LONG).show();

                        }
                    });
                }else{
                    Snackbar.make(v, "Debe Rellenar Todos los Campos", Snackbar.LENGTH_LONG).show();
                }


                //falta el node y en la interfaz y ver que no destrui registrar

            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModificarBicicleta.this).navigate(R.id.action_modificarBicicleta_to_modificarYEliminarBicicleta);
            }
        });

        return v;
    }
}