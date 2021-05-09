package com.upclicks.laDiva.viewModel;

import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.BuildConfig;
import com.upclicks.laDiva.data.ApiInterface;
import com.upclicks.laDiva.data.ApiService;
import com.upclicks.laDiva.pojo.request.LoginRequest;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.pojo.request.UserSession;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcountViewModel extends ViewModel {
    SignUpRequest signUpRequest ;
    LoginRequest loginRequest;
    ApiInterface apiInterface;


    MutableLiveData<Result<UserSession>> loginMutableLiveData = new MutableLiveData<>();
    public LiveData<Result<UserSession>> loginLiveData (){
        return loginMutableLiveData;
    }

    MutableLiveData<Result<SignUpRequest>> signUpMutableLiveData = new MutableLiveData<>();
    public LiveData<Result<SignUpRequest>> signUpLiveData (){
        return signUpMutableLiveData;
    }

   public MutableLiveData<String> loginerror = new MutableLiveData<>();

    MutableLiveData<String> signUperror = new MutableLiveData<>();

    public void login(String token) {

        loginRequest = new LoginRequest("201005886912","123456");
        apiInterface = ApiService.getINSTANCE().create(ApiInterface.class);

        Call<Result<UserSession>> call = apiInterface.login("application/json",
                Build.BRAND+" "+Build.MODEL,
                BuildConfig.VERSION_NAME,"5",
                String.valueOf(Build.VERSION.RELEASE),
                "0",token,"en",
                "0","0",
                loginRequest);
        call.enqueue(new Callback<Result<UserSession>>() {
            @Override
            public void onResponse(Call<Result<UserSession>> call, Response<Result<UserSession>> response) {



                Log.d("viewModel" , ""+response.code());
                if (response.isSuccessful()){

                    loginMutableLiveData.setValue(response.body());
                    Log.e("viewModel" , response.body().toString()+"test success");
                }else
                    Log.e("viewModel" , "not Successful");

            }

            @Override
            public void onFailure(Call<Result<UserSession>> call, Throwable t) {
                loginerror.setValue("error  "+t.getMessage());
                Log.e("viewModel" , t.getMessage());
            }
        });
    }
    public void signUp() {

        signUpRequest =new SignUpRequest("123456" , "Ahmed" , "Ali" , "01013723859" );

        apiInterface = ApiService.getINSTANCE().create(ApiInterface.class);

        Call<Result<SignUpRequest>> call = apiInterface.signUpRequest(signUpRequest);
        call.enqueue(new Callback<Result<SignUpRequest>>() {
            @Override
            public void onResponse(Call<Result<SignUpRequest>> call, Response<Result<SignUpRequest>> response) {
                if (response.isSuccessful()){
                    signUpMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Result<SignUpRequest>> call, Throwable t) {
                   signUperror.setValue(t.getMessage());
            }
        });
    }
    }

