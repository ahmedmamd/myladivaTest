package com.upclicks.laDiva.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;

public class BaseViewModel extends ViewModel {
    public MutableLiveData<String> requesterror = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkLogin = new MutableLiveData<>();
    public MutableLiveData<String> firebasetoken = new MutableLiveData<>();

    public LiveData<String> firebaseLiveData (){
        return firebasetoken;
    }
    public LiveData<Boolean> checkLogin (){
        return checkLogin;
    }
}
