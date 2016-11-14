package com.sladma.meteorite.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sladma.meteorite.data.model.Location;
import com.sladma.meteorite.data.model.Meteorite;
import com.sladma.meteorite.data.model.SimpleDate;
import com.sladma.meteorite.data.model.util.LocationDeserializer;
import com.sladma.meteorite.data.model.util.SimpleDateDeserializer;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * This interface is used by Retrofit library to access REST API by NASA.
 * <p>
 * Created by sladma
 */
public interface INasaAPI {

    // Replace with your app token
    // Can be generated at https://data.nasa.gov/login
    String NASA_APP_TOKEN = "J2E9vXwNLbCxwJPKhJTgcoMUW";

    @Headers({
            "Accept: application/json",
            "X-App-Token: " + NASA_APP_TOKEN
    })
    @GET("resource/y77d-th95")
    Call<List<Meteorite>> getAllMeteoriteLandings();

    class Factory {
        public static INasaAPI create() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(SimpleDate.class, SimpleDateDeserializer.INSTANCE)
                    .registerTypeAdapter(Location.class, LocationDeserializer.INSTANCE)
                    .create();
            return new Retrofit.Builder()
                    .baseUrl("https://data.nasa.gov/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .callFactory(new OkHttpClient().newBuilder().build())
                    .build()
                    .create(INasaAPI.class);
        }
    }

}