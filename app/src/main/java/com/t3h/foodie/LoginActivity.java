package com.t3h.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail, edtPassword;
    private Button btnLogin, btnRegister, btnForgotPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword= findViewById(R.id.edt_password);
        progressBar= findViewById(R.id.progressBar);
        btnForgotPassword=findViewById(R.id.btn_forgot_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.btn_forgot_password:
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
                break;
            case R.id.btn_login:
                String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();
                Pattern patternMail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
                if (email.isEmpty()) {
                    edtEmail.setError("Input email can not empty");
                    return;
                }
                if (!patternMail.matcher(email).matches()) {
                    edtEmail.setError("Wrong format email");
                    return;
                }

                if (password.isEmpty()) {
                    edtPassword.setError("Input password can not empty");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth = FirebaseAuth.getInstance();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                      if (!task.isSuccessful()){
                          if (password.length()< 6){
                               edtPassword.setError("Password too short, enter minimum 6 characters!");
                          }
                          else {
                              Toast.makeText(LoginActivity.this, "Check your email and password or sign up", Toast.LENGTH_SHORT).show();
                          }
                      }
                      else {
                          Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                          startActivity(intent);
                          finish();
                      }
                    }
                });
        }
    }
}
