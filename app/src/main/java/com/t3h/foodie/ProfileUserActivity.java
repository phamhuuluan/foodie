package com.t3h.foodie;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfileUserActivity<acct> extends AppCompatActivity implements View.OnClickListener, LocationListener {
    private ImageView imBack,imAvatar;
    private Button btnSignOut, btnAdress;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Uri imgUser;
    private TextView tvName, tvEmail, tvPhoneNumber, tvAdress,tvBlo;
    private LocationManager locationManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        initViews();
        initData();
        CheckPermission();
    }



    private void initViews() {
        tvBlo = findViewById(R.id.tv_blo);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        tvPhoneNumber = findViewById(R.id.tv_phone_number);
        tvAdress = findViewById(R.id.tv_diachi1);
        imBack = findViewById(R.id.im_back);
        imAvatar = findViewById(R.id.im_avt);
        btnAdress = findViewById(R.id.btn_adress);
        btnSignOut = findViewById(R.id.btn_sign_out);
        btnSignOut.setOnClickListener(this);
        imBack.setOnClickListener(this);
        btnAdress.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getLocation();
           }
       });


    }
    private void CheckPermission(){
        if (ContextCompat.checkSelfPermission(ProfileUserActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ProfileUserActivity.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,ProfileUserActivity.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void initData() {
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccount != null) {
            tvName.setText(googleSignInAccount.getDisplayName());
            tvEmail.setText(googleSignInAccount.getEmail());
            tvBlo.setText(googleSignInAccount.getFamilyName());
            tvPhoneNumber.setText(googleSignInAccount.getId());
            imgUser = googleSignInAccount.getPhotoUrl();
            Glide.with(imAvatar).load(imgUser).override(256, 256) .into(imAvatar);
         }
    }

    @Override
    public void onClick(View view) {
        auth = FirebaseAuth.getInstance();
        switch (view.getId()) {
            case R.id.im_back:
                Intent intent = new Intent(ProfileUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_sign_out:
                auth.signOut();
                Intent intentSignOut = new Intent(ProfileUserActivity.this, LoginActivity.class);
                startActivity(intentSignOut);
                break;

        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(ProfileUserActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            tvAdress.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}
