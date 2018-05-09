package openweathermap.poc.br.poc_openweathermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import openweathermap.poc.br.poc_openweathermap.adapters.FavoriteAdapter;
import openweathermap.poc.br.poc_openweathermap.adapters.RecyclerTouchListener;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;

public class HomeActivity extends AppCompatActivity {

    private static String TAG = HomeActivity.class.getName();

    @BindView(R.id.recycler_view_city_home)
    RecyclerView recyclerView;

    FavoriteAdapter mFavoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        setupRecyclerView();
    }

    @OnClick(R.id.btn_add_city)
    public void goToCitiesPage() {
        startActivity(new Intent(this, CitiesActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView(){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(getFavoriteAdapter());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Favorite favorite = mFavoriteAdapter.getList().get(position);

                Log.d(TAG, "Seleção: " + favorite.getWeather().getDescription());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private FavoriteAdapter getFavoriteAdapter(){

        return new FavoriteAdapter(this, new ArrayList<Favorite>());
    }
}
