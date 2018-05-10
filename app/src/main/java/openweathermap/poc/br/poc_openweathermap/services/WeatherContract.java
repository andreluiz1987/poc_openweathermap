package openweathermap.poc.br.poc_openweathermap.services;

import openweathermap.poc.br.poc_openweathermap.models.Favorite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public interface WeatherContract {

    @GET("/data/2.5/weather?lang=pt&units=metric")
    Call<Favorite> getTemperature(@Query("id") int cityId, @Query("appid") String appId);
}
