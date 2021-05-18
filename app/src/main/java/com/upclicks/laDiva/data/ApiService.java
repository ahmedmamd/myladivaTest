package com.upclicks.laDiva.data;


import android.content.Context;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "https://www.ladivaapp.com/";
    private ApiInterface apiInterface;
    private static ApiService INSTANCE;
    private static Retrofit retrofit;
    private static GenericInterciptor genericInterciptor;
    private static OkHttpClient client;


    public static Retrofit getINSTANCE(Context context) {
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

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }

//                .create(ApiService.class);

        return retrofit;
    }


}
