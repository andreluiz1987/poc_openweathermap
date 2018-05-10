package openweathermap.poc.br.poc_openweathermap.presenter;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmResults;
import openweathermap.poc.br.poc_openweathermap.DetailCityActivity;
import openweathermap.poc.br.poc_openweathermap.R;
import openweathermap.poc.br.poc_openweathermap.models.City;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;
import openweathermap.poc.br.poc_openweathermap.services.WeatherClient;
import openweathermap.poc.br.poc_openweathermap.services.WeatherContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * Created by andre.coelho on 10/05/2018.
 */

public class DetailPresenter implements DetailContract.Presenter, DetailContract.Actions {

    private static final String TAG = DetailPresenter.class.getName();

    private DetailContract.View view;
    private Favorite mFavorite;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    public Context getContext() {
        return (Context) view;
    }

    @Override
    public void getFavoriteData(int city_id, final boolean isFavorite) {

        view.showDialog();

        try {

            Realm realm = Realm.getDefaultInstance();
            final City city = realm.where(City.class).equalTo("id", city_id).findFirst();

            WeatherContract weatherContract = WeatherClient.getClient().create(WeatherContract.class);
            Call<Favorite> call = weatherContract.getTemperature(city_id, getContext().getString(R.string.appid));

            call.enqueue(new Callback<Favorite>() {
                @Override
                public void onResponse(Call<Favorite> call, Response<Favorite> response) {

                    Favorite favorite = response.body();
                    favorite.setCity(city);
                    favorite.setActive(true);

                    DetailPresenter.this.onSuccess(favorite, isFavorite);

                    view.hideDialog();
                }

                @Override
                public void onFailure(Call<Favorite> call, Throwable t) {
                    call.cancel();

                    Log.d(TAG, "onFailure " + t.getMessage());

                    view.hideDialog();
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            view.hideDialog();
        }
    }

    @Override
    public void saveFavorite() {

        Realm realm = Realm.getDefaultInstance();

        Favorite favoriteSave = realm.where(Favorite.class).equalTo("id", mFavorite.getId()).findFirst();

        if (favoriteSave == null) {

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mFavorite);
            realm.commitTransaction();
            realm.close();

            view.setFavoriteSelected();

        } else {

            view.setFavoriteUnselected();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults results = realm.where(Favorite.class).equalTo("id", mFavorite.getId()).findAll();
                    results.deleteAllFromRealm();
                }
            });
        }
    }

    @Override
    public void onSuccess(Favorite favorite, boolean isFavorite) {

        mFavorite = favorite;
        view.hideDialog();
        view.setData(mFavorite, isFavorite);
    }

    @Override
    public void onFailed() {
        view.hideDialog();
    }
}
