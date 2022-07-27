package com.example.sdjcomp;

import java.util.HashMap;

import kotlin.io.path.ExperimentalPathApi;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface IRetroFit {
    @POST("/login")
    Call<PreLoginUsuario> executeLogin(@Body HashMap<String,String> map);

    @POST("/registerUser")
    Call<Number> executeRegister(@Body Usuario map);

    @GET("/getUser/{correo}")
    Call<Usuario> executeGetUser(@Path("correo") String correo);

    @PUT("/updateUser")
    Call<Usuario> executeUpdateUser(@Body Usuario codigo);

    @DELETE("/deleteUser/{codigo}")
    Call<Number> executeDeleteUser(@Path("codigo") String codigo);
    
    //@FormUrlEncoded
    @PATCH("/updateOneUser/{palabras}")
    Call<Number> executeUpdateOneUser(@Path("palabras") String palabras);
}
