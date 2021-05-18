package com.upclicks.laDiva.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.upclicks.laDiva.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.UserSession;
import com.upclicks.laDiva.viewModel.AcountViewModel;

public class MainActivity extends AppCompatActivity  {


    AcountViewModel acountViewModel;

    String  TAG = "MainActivity";
    EditText email , password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acountViewModel = new ViewModelProvider(this).get(AcountViewModel.class);
        acountViewModel.setContext(this);

        email = findViewById(R.id.userEmail);
          password =findViewById(R.id.userPassword);



            findViewById(R.id.loginBtton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login();
                }
            });


       findViewById(R.id.signUP).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this , SignUpActivity.class);
               startActivity(intent);
           }
       });


    }

    private void login() {
        String UserEmail , UserPassword ;
        UserEmail = email.getText().toString();
        UserPassword = password.getText().toString();
        FirebaseApp.initializeApp(this);
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, instanceIdResult -> {
            String newToken = instanceIdResult.getToken();

            SharedPreferences sharedPreferences = getSharedPreferences("MyToken",MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("token", newToken);
            myEdit.commit();

            // baseViewModel.firebasetoken.setValue(newToken);
            Log.e("newToken", newToken);

            SystemClock.sleep(2000);
            acountViewModel.login(this,newToken , UserEmail , UserPassword) ;

        });

        acountViewModel.loginLiveData().observe(this, new Observer<Result<UserSession>>() {
            @Override
            public void onChanged(Result<UserSession> userSessionResult) {
                Log.d(TAG , ""+userSessionResult);
            }
        });

        acountViewModel.requesterror.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG , s);
            }
        });


    }





}