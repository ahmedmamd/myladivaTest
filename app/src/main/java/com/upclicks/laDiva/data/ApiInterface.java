package com.upclicks.laDiva.data;




import com.upclicks.laDiva.pojo.request.LoginRequest;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.pojo.request.UserSession;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("account/Authenticate")
    public Observable<Result<UserSession>> login(@Body LoginRequest body);

    @POST("account/Signup")
    public Observable<Result<SignUpRequest>> signUpRequest(@Body SignUpRequest signUpRequest);

}
