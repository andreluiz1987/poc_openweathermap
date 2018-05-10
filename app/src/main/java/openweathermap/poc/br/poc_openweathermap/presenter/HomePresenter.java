package openweathermap.poc.br.poc_openweathermap.presenter;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;

/**
 * Created by AndreCoelho on 09/05/2018.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
    }

    public Context getContext(){
        return (Context) view;
    }

    @Override
    public List<Favorite> getFavorite() {

        List<Favorite> favoriteList;

        Realm realm = Realm.getDefaultInstance();
        RealmResults results = realm.where(Favorite.class).equalTo("active", true).findAll();
        favoriteList = realm.copyFromRealm(results);
        realm.close();

        return favoriteList;
    }

    @Override
    public void onClick() {
    }
}
