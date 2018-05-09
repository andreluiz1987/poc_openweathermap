package openweathermap.poc.br.poc_openweathermap.services;

import openweathermap.poc.br.poc_openweathermap.models.Favorite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public interface WeatherContract {
    //http://api.openweathermap.org/data/2.5/weather?id=3470127&appid=8506ad2c74c3c69fbdd1708ec177fdf2&lang=pt&units=metric
    @GET("/data/2.5/weather?")
    Call<Favorite> getTemperature(@Query("id") int cityId, @Query("appid") String appId);
}
