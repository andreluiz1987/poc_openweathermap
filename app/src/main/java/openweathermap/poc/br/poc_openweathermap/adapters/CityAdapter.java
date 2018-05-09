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
import openweathermap.poc.br.poc_openweathermap.models.City;

/**
 * Created by AndreCoelho on 24/04/2018.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private Context context;
    private List<City> cities;

    public CityAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cities_rows, parent, false);

        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {

        City city = cities.get(position);
        holder.txtCityName.setText(city.getName());
        holder.txtItemCity.setText(String.valueOf(city.getName().charAt(0)).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void updateList(List<City> cities) {
        this.cities = cities;
        notifyDataSetChanged();
    }

    public List<City> getList() {
        return cities;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        public TextView txtCityName;
        public TextView txtItemCity;
        public CardView cardView;

        public CityViewHolder(View itemView) {
            super(itemView);

            txtCityName = itemView.findViewById(R.id.txt_city_name);
            txtItemCity = itemView.findViewById(R.id.txt_item_row_city);
        }
    }
}
