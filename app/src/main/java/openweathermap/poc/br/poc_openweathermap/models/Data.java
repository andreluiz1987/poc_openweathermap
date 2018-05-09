package openweathermap.poc.br.poc_openweathermap.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class Data {

    @SerializedName("data")
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
