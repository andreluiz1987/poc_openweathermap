package openweathermap.poc.br.poc_openweathermap.presenter;

import android.content.Context;

import java.util.List;

import openweathermap.poc.br.poc_openweathermap.models.Favorite;

/**
 * Created by AndreCoelho on 09/05/2018.
 */

public interface HomeContract {

    interface View{
        void navigateToFavoritePage(Favorite favorite);
    }

    interface Presenter{
        List<Favorite> getFavorite();
        Context getContext();
        void onClick();
    }
}
