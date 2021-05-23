package com.upclicks.laDiva.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.Log;


import com.google.firebase.BuildConfig;
import com.upclicks.laDiva.viewModel.AcountViewModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;



public class GenericInterciptor implements Interceptor {
    Context context ;
    AcountViewModel acountViewModel;
    Request request;

    public GenericInterciptor(Context context) {
        this.context = context;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyToken",MODE_PRIVATE);

//        String token ="cdR0OFC1igk:APA91bF-C4CkwlZe5x5pyLUG_WwkXqreKyLJonGr4CQnRv0XmJgn8cdkZqUPDGHlvnTCLPv0pT5C9NoEaFPyVHeb9a1UNWRCEFHqcDpbFKGnVsfXVk8TE3nQnMDiO-JvgHmpO_oW4J0J";
            String token = sharedPreferences.getString("token","");



                 request = chain.request()
                        .newBuilder()
                        .addHeader("LanguageCode", "en")
                        .addHeader("FirebaseToken",   token)
                        .addHeader("OSType", "0")
                        .addHeader("latitude","0")
                        .addHeader("longitude","0")
                        .addHeader("OSVersion", String.valueOf(Build.VERSION.RELEASE))
                        .addHeader("ConnectionType", "5")
                        .addHeader("AppVersion", BuildConfig.VERSION_NAME)
                        .addHeader("DeviceType", Build.BRAND+" "+Build.MODEL)
                        .addHeader("Accept", "application/json")
                        .build();

        Response response = chain.proceed(request);



        return response;
    }
}
