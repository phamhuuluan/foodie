package com.t3h.foodie.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.foodie.R;
import com.t3h.foodie.adapter.FoodAdapter;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Category;
import com.t3h.foodie.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {
    private RecyclerView rcFood;
    private Activity activity;
    private FoodAdapter foodAdapter;
    private Category category;
    private int page = 1;
    private int categoryId;

    public void setCategory(Category category) {
        this.category = category;
        this.categoryId = category.getId();
        initData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        rcFood = activity.findViewById(R.id.rc_food);
        foodAdapter = new FoodAdapter(activity.getLayoutInflater());
        rcFood.setAdapter(foodAdapter);
        initData();
    }

    private void initData() {
        Map<String, Integer> map = new HashMap<>();
        map.put("page", page);
        map.put("id_category", categoryId);
        ApiBuilder.getInstance().getFood(map).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> data = response.body();
                foodAdapter.setData(data);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(FoodFragment.class.getName(), t.toString());
            }
        });
    }
}
