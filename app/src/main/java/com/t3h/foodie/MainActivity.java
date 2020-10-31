package com.t3h.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.t3h.foodie.Fragment.FavoritesFragment;
import com.t3h.foodie.Fragment.HomeFragment;
import com.t3h.foodie.Fragment.ShoppingCartFragment;
import com.t3h.foodie.model.ShoppingCart;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnSignOut;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
    public static ArrayList<ShoppingCart> arrShc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        loadFragment(new HomeFragment());
    }
    private void initViews() {
        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navlistener);
        if (arrShc != null){
        }
        else {
            arrShc = new ArrayList<>();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment  = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    fragment  = new HomeFragment();
                    break;
                case R.id.nav_favorite:
                    fragment  = new FavoritesFragment();
                    break;
                case R.id.nav_cart:
                    ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
                    shoppingCartFragment.show(getSupportFragmentManager(), shoppingCartFragment.getTag());
                    break;
                case R.id.nav_user_profile:
                    Intent intent = new Intent(MainActivity.this, ProfileUserActivity.class);
                    startActivity(intent);
                    finish();
                    break;
             }
            return  loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}