package openweathermap.poc.br.poc_openweathermap.helpers;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import openweathermap.poc.br.poc_openweathermap.models.City;
import openweathermap.poc.br.poc_openweathermap.models.Data;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class Helpers {

    public static List<City> loadFileCitiesFromAsset(Context context) {

        List<City> cities;

        try {
            InputStream is = context.getAssets().open("city.list.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            cities = new Gson().fromJson(new String(buffer), Data.class).getCities();

            for(City city : cities){
                city.getCoord().setId(city.getId());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return cities;
    }
}

