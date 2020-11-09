package com.t3h.foodie.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.t3h.foodie.R;
import com.t3h.foodie.adapter.ProductAdapter;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment implements ProductAdapter.OnClickViewListener {
    private static final String TAG = "ProductFragment";
    private RecyclerView rcProduct;
    private ProductAdapter productAdapter;
    private Activity activity;
    private ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
    private int favorite = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_product, container , false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //product
        activity = getActivity();
        rcProduct = activity.findViewById(R.id.rc_product);
        productAdapter = new ProductAdapter(activity.getLayoutInflater());
        productAdapter.setListener(this);
        rcProduct.setAdapter(productAdapter);
        initData();
    }

    private void initData() {
        ApiBuilder.getInstance().getListProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> data = response.body();
                productAdapter.setData(data);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(HomeFragment.class.getName(), t.toString());
            }
        });
    }


    private void showFrament(Fragment fragment) {
        FragmentTransaction frProduct = getFragmentManager().beginTransaction();
        frProduct.replace(R.id.frame_container, fragment);
        frProduct.commit();
    }

    @Override
    public void clickAddCart(Product product) {
        ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();
        shoppingCartFragment.show(getChildFragmentManager(), shoppingCartFragment.getTag());
    }

    @Override
    public void clickFavorite(Product product) {
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(activity);
        String userId = googleSignInAccount.getId();
        int product_id = product.getId();
        Map<String, Object> mapint = new HashMap<>();
        mapint.put("user_firebase", userId);
        mapint.put("product_id", product_id);
        ApiBuilder.getInstance().insertId(mapint).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });
       
    }
}
