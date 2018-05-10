package openweathermap.poc.br.poc_openweathermap.helpers;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by AndreCoelho on 09/05/2018.
 */

public class MessageHelpers {

    public static void showToast(final Context context, final String text) {

        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }
}


