package com.upclicks.laDiva.viewModel;

import android.content.Context;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.upclicks.laDiva.base.BaseViewModel;
import com.upclicks.laDiva.data.ApiInterface;
import com.upclicks.laDiva.data.ApiService;
import com.upclicks.laDiva.data.CustomRxObserver;
import com.upclicks.laDiva.pojo.request.LoginRequest;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.pojo.request.UserSession;
import com.upclicks.laDiva.repository.AccountRepository;


import javax.inject.Inject;

import dagger.Provides;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
//@HiltViewModel
public  class  AcountViewModel extends BaseViewModel {
    SignUpRequest signUpRequest;
    LoginRequest loginRequest;
    ApiInterface apiInterface;
    AccountRepository accountRepository;
    Context context;

@Inject
    public AcountViewModel() {
    }

     @ViewModelInject
     public AcountViewModel(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public void setContext(Context context) {
        this.context = context;
        apiInterface = ApiService.getINSTANCE(context);
//                .create(ApiInterface.class);
        Log.d("apiinterface" ,"setContext: "+apiInterface.toString());
        accountRepository = new AccountRepository(apiInterface);
    }


    MutableLiveData<Result<UserSession>> loginMutableLiveData = new MutableLiveData<>();

    public LiveData<Result<UserSession>> loginLiveData() {
        return loginMutableLiveData;
    }

    MutableLiveData<Result<SignUpRequest>> signUpMutableLiveData = new MutableLiveData<>();

    public LiveData<Result<SignUpRequest>> signUpLiveData() {
        return signUpMutableLiveData;
    }

    public void login(Context context, String token, String email, String password) {
        loginRequest = new LoginRequest(email, password);
        // loginRequest = new LoginRequest("201005886912","123456");
        accountRepository.login(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver() {

                    @Override
                    public void onNext(@NonNull Object o) {
                        loginMutableLiveData.setValue((Result<UserSession>) o);
                        Log.d("onsuccess", "onNext: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        requesterror.setValue("error" + e.getMessage());
                        Log.d("onerror", "onError: ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });


//         observable.subscribe(o -> loginMutableLiveData.setValue(o) , e-> requesterror.setValue("error"+e) );

    }

    public void signUp(Context context, String password, String name, String surname, String phoneNumber) {

        signUpRequest = new SignUpRequest(password, name, surname, phoneNumber);

        accountRepository.signUp(signUpRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .unsubscribeOn(Schedulers.io())
                .subscribe(new CustomRxObserver() {
                    @Override
                    public void onNext(@NonNull Object o) {
                        signUpMutableLiveData.setValue((Result<SignUpRequest>) o);
                        Log.d("onsuccess", "onNext: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        requesterror.setValue("en error" + e.getMessage());
                        Log.d("onerror", "onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}

