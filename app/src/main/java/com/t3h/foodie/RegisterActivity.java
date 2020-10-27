package com.t3h.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail, edtPassword, edtPasswordConfirm;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button btnRegister, btnCanel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtPasswordConfirm = findViewById(R.id.edt_password_confirm);
        progressBar= findViewById(R.id.progressBar);
        btnRegister= findViewById(R.id.btn_register);
        btnCanel= findViewById(R.id.btn_cancel);
        btnRegister.setOnClickListener(this);
        btnCanel.setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
         switch (view.getId()) {
             case R.id.btn_register:
                 String email = edtEmail.getText().toString().trim();
                 String password = edtPassword.getText().toString().trim();
                 String passwordConfirm = edtPasswordConfirm.getText().toString().trim();
                 Pattern patternMail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
                 if (email.isEmpty()) {
                     edtEmail.setError("Email Can not empty");
                     return;
                 }
                 if (!patternMail.matcher(email).matches()) {
                     edtEmail.setError("Wrong format email");
                     return;
                 }
                 if (password.isEmpty()) {
                     edtPassword.setError("Can not empty");
                     return;
                 }
                 if (password.length() < 6) {
                     edtPassword.setError("Password too short, enter minimum 6 characters!");
                     return;
                 }
                 if (passwordConfirm.isEmpty()) {
                     edtPasswordConfirm.setError("Can not empty");
                     return;
                 }
                 if (!passwordConfirm.equals(password)) {
                     edtPasswordConfirm.setError("Re confirm password not equal password");
                     return;
                 }
                 progressBar.setVisibility(View.VISIBLE);
                 auth = FirebaseAuth.getInstance();
                 auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         Toast.makeText(RegisterActivity.this, "onComplete:" +
                                          task.isSuccessful(), Toast.LENGTH_SHORT).show();
                         progressBar.setVisibility(view.GONE);
                         if (!task.isSuccessful()) {
                             Toast.makeText(RegisterActivity.this, "Authentication failed." +
                                             task.getException(),Toast.LENGTH_SHORT).show();
                         } else {
                             startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                             finish();
                         }
                     }
                 });
                 break;
             case R.id.btn_cancel:
                 finish();
                 break;
         }
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
