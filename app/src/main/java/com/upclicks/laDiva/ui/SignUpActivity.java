package com.upclicks.laDiva.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.upclicks.laDiva.R;
import com.upclicks.laDiva.pojo.request.Result;
import com.upclicks.laDiva.pojo.request.SignUpRequest;
import com.upclicks.laDiva.viewModel.AcountViewModel;

public class SignUpActivity extends AppCompatActivity {
    AcountViewModel acountViewModel;
    String TAG ="signUP";
    EditText name , surname , password , phoneNumber ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
        acountViewModel = new ViewModelProvider(this).get(AcountViewModel.class);
        acountViewModel.setContext(this);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        password = findViewById(R.id.password);
        phoneNumber =findViewById(R.id.emailOrPhone);

        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void signUp() {
        String userName ,userPassword , userSurname ,userPhoneNumber ;
        userName = name.getText().toString();
        userPassword = password.getText().toString();
        userSurname = surname.getText().toString();
        userPhoneNumber = phoneNumber.getText().toString();
        acountViewModel.signUp(this , userPassword , userName , userSurname , userPhoneNumber);
        acountViewModel.signUpLiveData().observe(this, new Observer<Result<SignUpRequest>>() {
            @Override
            public void onChanged(Result<SignUpRequest> signUpRequestResult) {
                Log.e(TAG , signUpRequestResult.toString());
            }
        });
    }


}