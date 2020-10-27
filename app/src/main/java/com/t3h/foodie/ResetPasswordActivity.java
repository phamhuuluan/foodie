package com.t3h.foodie;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail;
    private ImageView imBack;
    private Button btnSend;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        initViews();
    }

    private void initViews() {
        edtEmail= findViewById(R.id.edt_email);
        btnSend = findViewById(R.id.btn_send);
        imBack = findViewById(R.id.im_back);
        progressBar = findViewById(R.id.progressBar);
        edtEmail.setImeOptions(EditorInfo.IME_ACTION_DONE);
        btnSend.setOnClickListener(this);
        imBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        auth = FirebaseAuth.getInstance();
        switch (view.getId()){
            case R.id.im_back:
                finish();
                break;
            case  R.id.btn_send:
                String emaill = edtEmail.getText().toString().trim();
                if (emaill.isEmpty()){
                    edtEmail.setError("Input email can not empty");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(emaill).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ResetPasswordActivity.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.GONE);
                    }
                });
        }
    }
}
