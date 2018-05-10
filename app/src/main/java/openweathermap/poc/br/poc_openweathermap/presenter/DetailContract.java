package openweathermap.poc.br.poc_openweathermap.presenter;

import openweathermap.poc.br.poc_openweathermap.models.Favorite;

/**
 * Created by andre.coelho on 10/05/2018.
 */

public interface DetailContract {

    interface View {
        void showDialog();

        void hideDialog();

        void setData(Favorite favorite, boolean isFavorite);

        void setFavoriteSelected();
        void setFavoriteUnselected();
    }

    interface Presenter {
        void getFavoriteData(int city_id, boolean isFavorite);
        void saveFavorite();
    }

    interface Actions {
        void onSuccess(Favorite favorite, boolean isFavorite);
        void onFailed();
    }
}
