package com.t3h.foodie.Fragment;

import android.app.Activity;
import android.os.Bundle;
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

import com.t3h.foodie.MainActivity;
import com.t3h.foodie.R;
import com.t3h.foodie.adapter.ProductAdapter;
import com.t3h.foodie.api.Api;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductFragment extends Fragment implements ProductAdapter.OnClickViewListener {
    private RecyclerView rcProduct;
    private ProductAdapter productAdapter;
    private Activity activity;
    private ShoppingCartFragment shoppingCartFragment = new ShoppingCartFragment();

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
}
