package openweathermap.poc.br.poc_openweathermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import openweathermap.poc.br.poc_openweathermap.adapters.FavoriteAdapter;
import openweathermap.poc.br.poc_openweathermap.adapters.RecyclerTouchListener;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;
import openweathermap.poc.br.poc_openweathermap.presenter.HomeContract;
import openweathermap.poc.br.poc_openweathermap.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private static String TAG = HomeActivity.class.getName();

    @BindView(R.id.recycler_view_city_home)
    RecyclerView recyclerView;
    @BindView(R.id.txt_city_empty)
    TextView txtEmptyFavorite;

    HomeContract.Presenter mPresenter;
    //FavoriteAdapter mFavoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        mPresenter = new HomePresenter(this);
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
    protected void onResume() {
        super.onResume();

        setupRecyclerView();
        loadFavorites();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, recyclerTouchListene));
    }

    public RecyclerTouchListener.ClickListener recyclerTouchListene = new RecyclerTouchListener.ClickListener() {

        @Override
        public void onClick(View view, int position) {

            mPresenter.onClick();

            Favorite favorite = ((FavoriteAdapter)recyclerView.getAdapter()).getList().get(position);
            navigateToFavoritePage(favorite);

//            Intent intent = new Intent(HomeActivity.this, DetailCityActivity.class);
//            intent.putExtra("CITY_ID", favorite.getCity().getId());
//            intent.putExtra("FAVORITE", true);
//
//            startActivity(intent);
        }

        @Override
        public void onLongClick(View view, int position) {

        }
    };

    public void navigateToFavoritePage(Favorite favorite){

        Intent intent = new Intent(HomeActivity.this, DetailCityActivity.class);
        intent.putExtra("CITY_ID", favorite.getCity().getId());
        intent.putExtra("FAVORITE", true);

        startActivity(intent);
    }

    private void loadFavorites() {

        FavoriteAdapter adapter = new FavoriteAdapter(this, mPresenter.getFavorite());

        if (adapter.getList().size() == 0) {
            recyclerView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            txtEmptyFavorite.setVisibility(View.GONE);
        }

        recyclerView.setAdapter(adapter);
    }

//    private FavoriteAdapter getFavoriteAdapter() {
//
//        List<Favorite> favoriteList = getFavorites();
//        return new FavoriteAdapter(this, favoriteList);
//    }

//    private List<Favorite> getFavorites() {
//
//        List<Favorite> favoriteList = new ArrayList<>();
//
//        Realm realm = Realm.getDefaultInstance();
//        RealmResults results = realm.where(Favorite.class).equalTo("active", true).findAll();
//        favoriteList = realm.copyFromRealm(results);
//        realm.close();
//
//        return favoriteList;
//    }

}
