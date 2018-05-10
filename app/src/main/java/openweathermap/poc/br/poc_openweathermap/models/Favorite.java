package openweathermap.poc.br.poc_openweathermap.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class Favorite  extends RealmObject {

    @PrimaryKey
    private int id;
    private City city;
    private Main main;
    private RealmList<Weather> weather;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public RealmList<Weather>  getWeathers() {
        return weather;
    }

    public void setWeather(RealmList<Weather>  weathers) {
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
