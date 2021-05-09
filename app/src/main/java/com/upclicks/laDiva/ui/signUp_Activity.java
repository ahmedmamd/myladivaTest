package com.upclicks.laDiva.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.upclicks.laDiva.R;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.viewModel.AcountViewModel;

public class signUp_Activity extends AppCompatActivity {
    AcountViewModel acountViewModel;
    String TAG ="signUP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        acountViewModel = new ViewModelProvider(this).get(AcountViewModel.class);
        signUp();
    }

    private void signUp() {
        acountViewModel.signUp();
        acountViewModel.signUpLiveData().observe(this, new Observer<Result<SignUpRequest>>() {
            @Override
            public void onChanged(Result<SignUpRequest> signUpRequestResult) {
                Log.e(TAG , signUpRequestResult.toString());
            }
        });
    }


}