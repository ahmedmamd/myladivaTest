package com.upclicks.laDiva.repository;

import com.upclicks.laDiva.data.ApiInterface;
import com.upclicks.laDiva.pojo.request.LoginRequest;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.pojo.request.UserSession;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;


public class AccountRepository  {

    ApiInterface apiInterface ;



   @Inject
   public AccountRepository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }

    public Observable <Result<UserSession>> login(LoginRequest loginRequest){

        return apiInterface.login(loginRequest);
    }

    public Observable <Result<SignUpRequest>> signUp(SignUpRequest signUpRequest){
        return apiInterface.signUpRequest(signUpRequest);
    }

}
