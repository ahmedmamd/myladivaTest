package com.upclicks.laDiva.data;


import android.content.Context;

import com.upclicks.laDiva.component.ApiComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.components.SingletonComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
@Module
@InstallIn(SingletonComponent.class)
public class ApiService {
    private static final String BASE_URL = "https://www.ladivaapp.com/";
//    private static String BASE_URL;
    private static ApiInterface apiInterface;
    private static ApiService INSTANCE;

   private static Retrofit retrofit;
    private static GenericInterciptor genericInterciptor;
    private static OkHttpClient client;


@Provides
@Singleton
    public static ApiInterface getINSTANCE(Context context) {

        genericInterciptor = new GenericInterciptor(context);
//        if (INSTANCE == null) {
//            INSTANCE = new ApiService();
//        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(genericInterciptor)
                .build();

        if (apiInterface == null) {
            apiInterface = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build().create(ApiInterface.class);
            //apiInterface = retrofit.create(ApiInterface.class);

        }

//                .create(ApiService.class);

        return apiInterface;
    }


}
