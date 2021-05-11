package com.upclicks.laDiva.data;


import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
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
                    .build();
        }

//                .create(ApiService.class);

        return retrofit;
    }


}
