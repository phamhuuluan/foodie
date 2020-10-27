package com.t3h.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileUserActivity extends AppCompatActivity implements View.OnClickListener {
     private ImageView imBack, imAvatar;
     private Button btnSignOut;
     private FirebaseAuth auth;
     private FirebaseUser user;
     private TextView tvName, tvEmail, tvPhoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        initViews();
        initData();
    }

    private void initViews() {
        tvName= findViewById(R.id.tv_name);
        tvEmail= findViewById(R.id.tv_email);
        tvPhoneNumber= findViewById(R.id.tv_phone_number);
        imBack= findViewById(R.id.im_back);
        imAvatar=findViewById(R.id.im_avt);
        btnSignOut=findViewById(R.id.btn_sign_out);
        btnSignOut.setOnClickListener(this);
        imBack.setOnClickListener(this);
    }
    private void initData() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        tvName.setText(user.getDisplayName());
        tvEmail.setText(user.getEmail());
        tvPhoneNumber.setText(user.getPhoneNumber());
    }
    @Override
    public void onClick(View view) {
        auth = FirebaseAuth.getInstance();
        switch (view.getId()){
            case R.id.im_back:
                Intent intent = new Intent(ProfileUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.btn_sign_out:
                auth.signOut();
                Intent intentSignOut = new Intent(ProfileUserActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
                break;
        }
    }
}
