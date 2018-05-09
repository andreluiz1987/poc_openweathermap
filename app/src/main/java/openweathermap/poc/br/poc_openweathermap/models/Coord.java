package openweathermap.poc.br.poc_openweathermap.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class Coord  extends RealmObject {

    @PrimaryKey
    private int id;
    private float lon;
    private float lat;

    public int getId() {
       return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
