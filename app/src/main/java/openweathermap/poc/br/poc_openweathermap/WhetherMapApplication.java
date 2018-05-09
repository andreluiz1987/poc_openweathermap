package openweathermap.poc.br.poc_openweathermap;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class WhetherMapApplication extends Application {

    private static WhetherMapApplication instance;

    public static WhetherMapApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Realm.init(this);
    }
}
