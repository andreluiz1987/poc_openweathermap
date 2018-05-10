package openweathermap.poc.br.poc_openweathermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import openweathermap.poc.br.poc_openweathermap.Connections.ConnectionNet;
import openweathermap.poc.br.poc_openweathermap.helpers.MessageHelpers;
import openweathermap.poc.br.poc_openweathermap.models.City;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;
import openweathermap.poc.br.poc_openweathermap.services.WeatherClient;
import openweathermap.poc.br.poc_openweathermap.services.WeatherContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class DetailCityActivity extends AppCompatActivity {

    private static final String TAG = DetailCityActivity.class.getName();

    @BindView(R.id.txt_city_name)
    TextView txtCityName;
    @BindView(R.id.txt_date_now)
    TextView txtDateNoew;
    @BindView(R.id.txt_weather_now)
    TextView txtWeather;
    @BindView(R.id.txt_city_temp)
    TextView txtTemp;
    @BindView(R.id.img_favorite)
    ImageView imgFavorite;
    @BindView(R.id.panel_progress)
    LinearLayout pnlProgress;

    Favorite mFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_city);

        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getString(R.string.title_cities));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int city_id = getIntent().getIntExtra("CITY_ID", 0);
        boolean isFavorite = getIntent().getBooleanExtra("FAVORITE", false);

        // TODO: 10/05/2018 tratar exceções

        showProgressBar(View.VISIBLE);

        if (ConnectionNet.hasConnection(this)) {
            getDetailCity(city_id, isFavorite);
        } else {
            MessageHelpers.showToast(this, "Verifique sua conexão com a internet.");
            showProgressBar(View.GONE);
        }
    }

    private void showProgressBar(int visible) {
        pnlProgress.setVisibility(visible);
    }

    @OnClick(R.id.img_favorite)
    public void setFavorite() {

        Realm realm = Realm.getDefaultInstance();

        Favorite favoriteSave = realm.where(Favorite.class).equalTo("id", mFavorite.getId()).findFirst();

        if (favoriteSave == null) {

            realm.beginTransaction();
            realm.copyToRealmOrUpdate(mFavorite);
            realm.commitTransaction();
            realm.close();

            imgFavorite.setImageResource(R.drawable.ic_favorite_selected);

        } else {

            imgFavorite.setImageResource(R.drawable.ic_star_unselected);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults results = realm.where(Favorite.class).equalTo("id", mFavorite.getId()).findAll();
                    results.deleteAllFromRealm();
                }
            });
        }
    }

    private void showData(final boolean isFavorite) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                txtCityName.setText(mFavorite.getCity().getName());
                txtDateNoew.setText(new Date().toString());
                txtWeather.setText(mFavorite.getWeathers().get(0).getDescription());
                txtTemp.setText(Math.round(mFavorite.getMain().getTemp()) + "ºC");

                if (isFavorite)
                    imgFavorite.setImageResource(R.drawable.ic_favorite_selected);
                else
                    imgFavorite.setImageResource(R.drawable.ic_star_unselected);
            }
        });
    }

    private void getDetailCity(int city_id, final boolean isFavorite) {

        Realm realm = Realm.getDefaultInstance();
        final City city = realm.where(City.class).equalTo("id", city_id).findFirst();

        WeatherContract weatherContract = WeatherClient.getClient().create(WeatherContract.class);
        Call<Favorite> call = weatherContract.getTemperature(city_id, getString(R.string.appid));

        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {

                mFavorite = response.body();
                mFavorite.setCity(city);
                mFavorite.setActive(true);

                showData(isFavorite);

                showProgressBar(GONE);
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                call.cancel();

                Log.d(TAG, "onFailure " + t.getMessage());

                showProgressBar(GONE);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
