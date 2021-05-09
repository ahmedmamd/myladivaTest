package com.upclicks.laDiva.data;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static final String BASE_URL = "https://www.ladivaapp.com/";
    private ApiInterface apiInterface;
    private static ApiService INSTANCE;
    private static Retrofit retrofit;

//    public ApiService() {
//        getInstance();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        apiInterface = retrofit.create(ApiInterface.class);
//
//    }

    public static Retrofit getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new ApiService();
//        }
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
         if (retrofit == null){
             retrofit = new Retrofit.Builder()
                     .baseUrl(BASE_URL)
                     .client(client)
                     .addConverterFactory(GsonConverterFactory.create())
                     .build();
         }

//                .create(ApiService.class);

        return retrofit;
    }

//    public Call<Result<LoginRequest>> checklogin(LoginRequest loginRequest){
//        return (Call<Result<LoginRequest>>) apiInterface.login(loginRequest);
//    }
//    public Observable<Result<AccessToken>> getlogin(String username, String password){
//        return apiInterface.getlogin(username,password);
//    }
//
//    public Call<Result<LoginRequest>> getcallLogin(String username, String password){
//        return apiInterface.loginCall(username,password);
//    }
}
