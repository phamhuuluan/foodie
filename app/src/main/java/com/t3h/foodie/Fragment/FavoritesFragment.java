package com.t3h.foodie.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.foodie.R;
import com.t3h.foodie.adapter.FavoriteAdapter;
import com.t3h.foodie.adapter.ProductAdapter;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {
    private RecyclerView rcFavorite;
    private Activity activity;
    private FavoriteAdapter favoriteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        rcFavorite = activity.findViewById(R.id.rc_favorite);
        favoriteAdapter = new FavoriteAdapter(activity.getLayoutInflater());
        rcFavorite.setAdapter(favoriteAdapter);
        initData();
    }

    private void initData() {
        ApiBuilder.getInstance().getListFavorite().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> data = response.body();
                favoriteAdapter.setData(data);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(HomeFragment.class.getName(), t.toString());
            }
        });
    }
}

