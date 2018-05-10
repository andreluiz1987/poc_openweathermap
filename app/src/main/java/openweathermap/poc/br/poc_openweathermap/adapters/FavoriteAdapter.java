package openweathermap.poc.br.poc_openweathermap.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import openweathermap.poc.br.poc_openweathermap.R;
import openweathermap.poc.br.poc_openweathermap.models.Favorite;

/**
 * Created by AndreCoelho on 08/05/2018.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private List<Favorite> favorites;

    public FavoriteAdapter(Context context, List<Favorite> favorites) {
        this.context = context;
        this.favorites = favorites;
    }

    @Override
    public FavoriteAdapter.FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_row, parent, false);

        return new FavoriteAdapter.FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.FavoriteViewHolder holder, int position) {

        Favorite favorite = favorites.get(position);

        holder.txtCityTemp.setText(Math.round(favorite.getMain().getTemp()) + "ºC");
        holder.txtCityWeather.setText(favorite.getWeathers().get(0).getMain());

        holder.txtCityName.setText(favorite.getCity().getName());
        holder.txtItemCity.setText(String.valueOf(favorite.getCity().getName().charAt(0)).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public void updateList(List<Favorite> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    public List<Favorite> getList() {
        return favorites;
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCityTemp;
        public TextView txtCityWeather;
        public TextView txtCityName;
        public TextView txtItemCity;
        public CardView cardView;

        public FavoriteViewHolder(View itemView) {
            super(itemView);

            txtCityTemp = itemView.findViewById(R.id.txt_city_temp);
            txtCityWeather = itemView.findViewById(R.id.txt_city_weather);
            cardView = itemView.findViewById(R.id.card_view_city_favorite);
            txtCityName = itemView.findViewById(R.id.txt_city_name);
            txtItemCity = itemView.findViewById(R.id.txt_item_row_city);
        }
    }
}
