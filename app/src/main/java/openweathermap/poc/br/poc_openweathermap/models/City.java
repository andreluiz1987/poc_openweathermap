package openweathermap.poc.br.poc_openweathermap.models;

import java.util.Comparator;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class City extends RealmObject implements Comparator<City> {

    @PrimaryKey
    private int id;
    private Coord coord;
    private String country;
    private String name;
    private int zoom;
    private String weather;
    private int temperature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public int compare(City city, City t1) {
        return city.getName().compareTo(t1.getName());
    }
}

