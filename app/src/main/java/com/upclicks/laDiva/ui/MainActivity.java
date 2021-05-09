package com.upclicks.laDiva.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.upclicks.laDiva.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.UserSession;
import com.upclicks.laDiva.viewModel.AcountViewModel;

public class MainActivity extends AppCompatActivity  {


    AcountViewModel acountViewModel;
    String  TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acountViewModel = new ViewModelProvider(this).get(AcountViewModel.class);

          login();


       findViewById(R.id.signUP).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this , signUp_Activity.class);
               startActivity(intent);
           }
       });


    }

    private void login() {
        FirebaseApp.initializeApp(this);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();
            Log.e("newToken", newToken);
            acountViewModel.login(newToken);
        });

        acountViewModel.loginLiveData().observe(this, new Observer<Result<UserSession>>() {
            @Override
            public void onChanged(Result<UserSession> userSessionResult) {
                Log.d(TAG , ""+userSessionResult);
            }
        });

        acountViewModel.loginerror.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG , s);
            }
        });


    }





}