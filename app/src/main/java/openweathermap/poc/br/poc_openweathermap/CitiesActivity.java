package openweathermap.poc.br.poc_openweathermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import openweathermap.poc.br.poc_openweathermap.adapters.CityAdapter;
import openweathermap.poc.br.poc_openweathermap.adapters.RecyclerTouchListener;
import openweathermap.poc.br.poc_openweathermap.helpers.Helpers;
import openweathermap.poc.br.poc_openweathermap.models.City;

public class CitiesActivity extends AppCompatActivity {

    private static String TAG = CitiesActivity.class.getName();

    @BindView(R.id.recycler_view_city)
    RecyclerView recyclerView;

    CityAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        getSupportActionBar().setTitle(getString(R.string.title_cities));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        setupRecyclerView();
    }

    private void setupRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(getCityAdapter());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                City city = mCityAdapter.getList().get(position);

                Log.d(TAG, "Cidade selecionada: " + city.getName());

                Intent intent = new Intent(CitiesActivity.this, DetailCityActivity.class);
                intent.putExtra("CITY_ID", city.getId());
                intent.putExtra("FAVORITE", false);

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private CityAdapter getCityAdapter(){

        Realm realm = Realm.getDefaultInstance();

        RealmResults<City>  results = realm.where(City.class).findAll();
        List<City> cities = realm.copyFromRealm(results);

        if(cities.size() == 0) {

            cities = Helpers.loadFileCitiesFromAsset(this);

            realm.beginTransaction();
            realm.insertOrUpdate(cities);
            realm.commitTransaction();
            realm.close();
        }

        mCityAdapter = new CityAdapter(this, cities);

        return mCityAdapter;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
