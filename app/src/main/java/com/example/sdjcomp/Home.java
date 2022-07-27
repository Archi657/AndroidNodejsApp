package com.example.sdjcomp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.sdjcomp.databinding.HomeBinding;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home extends Fragment {

    private HomeBinding binding;
    private Retrofit retrofit;
    private IRetroFit iRetrofit;
    private String URL="http://192.168.1.14:3000/login/";
    private boolean validado;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View v = inflater.inflate(R.layout.home,container,false);
        Button btnIniciar = (Button) v.findViewById(R.id.btnIniciarSeseion);
        Button btnRegistrarse = (Button) v.findViewById(R.id.btnRegistrarse);
        binding = HomeBinding.inflate(inflater, container, false);

        retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        iRetrofit = retrofit.create(IRetroFit.class);

        btnIniciar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText txtCorreo = (EditText) v.findViewById(R.id.txtCorreo);
                EditText txtClave = (EditText) v.findViewById(R.id.txtClave);


                HashMap<String,String> map = new HashMap<>();
                map.put("correo",txtCorreo.getText().toString());
                map.put("clave",txtClave.getText().toString());

                Call<PreLoginUsuario> call = iRetrofit.executeLogin(map);
                call.enqueue(new Callback<PreLoginUsuario>() {
                    @Override
                    public void onResponse(Call<PreLoginUsuario> call, Response<PreLoginUsuario> response) {
                        System.out.println(response.code());
                        //si la bd devuel 200 y luego se revisa por segunda vez si coinciden
                        if(response.code()==200)
                        {

                            PreLoginUsuario result = response.body();
                            System.out.println(map.get("correo")+" " + result.getCorreo() + " " + map.get("correo").equals(result.getCorreo()));
                            if(map.get("correo").equals(result.getCorreo()) && map.get("clave").equals(result.getClave()))
                            {
                                System.out.println("JULIAN!!!¡");
                                validado=true;
                                ((Sesion) getActivity().getApplicationContext()).setCorreo(txtCorreo.getText().toString());
                                NavHostFragment.findNavController(Home.this).navigate(R.id.action_Home_to_InterfazEstudiante);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<PreLoginUsuario> call, Throwable t) {
                        //cuando no coinciden las credenciales
                        validado=false;
                        //TODO QUItar
                        System.out.println("BULIAN1!!!¡");
                        Toast.makeText(getContext(), "UPSI", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Home.this).navigate(R.id.action_Home_to_fragment_registro);
            }
        });

        return v;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void handleLoginDialog(LayoutInflater inflater, ViewGroup container) {
        View v = inflater.inflate(R.layout.home,container,false);
        //AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setView(v).show();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}