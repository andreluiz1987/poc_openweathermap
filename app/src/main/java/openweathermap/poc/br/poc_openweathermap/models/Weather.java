package openweathermap.poc.br.poc_openweathermap.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class Weather extends RealmObject {

    @PrimaryKey
    private int id;
    private String main;
    private String Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
