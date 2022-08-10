package com.example.sdjcomp;

import java.util.HashMap;
import java.util.List;

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

    /*
    * @METHOD_HTTP
    * Call<Clase a la que convierte> nombreMetodo(@Body para enviar la info apra el match en el cuerpo de la peticion
    *                                              @Path para enviar por la ruta de la petion como un get)
    *
    * */

    @POST("/login")
    Call<PreLoginUsuario> executeLogin(@Body HashMap<String,String> map);

    @POST("/registrarUsuario")
    Call<Number> executeRegister(@Body Usuario map);

    @GET("/getUser/{correo}")
    Call<Usuario> executeGetUser(@Path("correo") String correo);

    @PUT("/updateUser")
    Call<Usuario> executeUpdateUser(@Body Usuario codigo);

    @POST("/updatePassword")
    Call<Number> executeUpdatePassword(@Body Usuario codigo);

    @DELETE("/deleteUser/{codigo}")
    Call<Number> executeDeleteUser(@Path("codigo") String codigo);
    
    @PATCH("/updateOne/{palabras}")
    Call<Number> executeUpdateOneUser(@Path("palabras") String palabras);

    //BICICLETAS
    @GET("/getBikes/{codigo}")
    Call<List<Bicicleta>> executeGetBikes(@Path("codigo") String codigo);

    @GET("/getBike/{codigo}/{id}")
    Call<Bicicleta> executeGetBike(@Path("codigo") String codigo, @Path("id") int id);

    @GET("/getOne/{palabras}")
    Call<Usuario> executeGetUserByCode(@Path("palabras") String palabras);

    @POST("/registerParqueadero/{parqueadero}")
    Call<Parqueadero> executeCreateParqueadero(@Path("parqueadero") String parqueadero);

    @GET("/getAll/{tabla}")
    Call<List<Pregunta>> executeGetAll(@Path("tabla") String tabla);

    @GET("/getCupo/{seccion}")
    Call<List<Cupo>> executeGetCuposEnable(@Path("seccion") String seccion);

    @GET("/getParqueadero/{seccion}")
    Call<List<Bicicleta>> executeGetParqueaderos(@Path("seccion") String seccion);

    @GET("/getBikeForDesasignar/{numSerie}")
    Call<BicicletaParaBorrar> executeGetBikeForDelete(@Path("numSerie") String numSerie);
    @DELETE("/deleteParqueadero/{idBicicleta}")
    Call<Number> executeDeleteParqueadero(@Path("idBicicleta") Number idBicicleta);
    @GET("/getMarcas")
    Call<List<Marca>> executeGetMarca();

    @GET("/getTipos")
    Call<List<Tipo>> executeGetTipos();

    @POST("/registerBike")
    Call<Number> executeRegisterBike(@Body BicicletaRegistrar bici);

    @PUT("/updateBicicleta")
    Call<Number> executeUpdateBicicleta(@Body BicicletaRegistrar bici);

    @DELETE("/deleteBicicleta/{idBicicleta}")
    Call<Number> executeDeleteBicicleta(@Path("idBicicleta") int idBicicleta);
}
