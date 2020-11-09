package com.t3h.foodie.Fragment;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

//import com.android.volley.RequestQueue;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
import com.t3h.foodie.R;
import com.t3h.foodie.adapter.BestSellingAdapter;
import com.t3h.foodie.adapter.CategoryAdapter;
import com.t3h.foodie.adapter.ProductAdapter;
import com.t3h.foodie.api.ApiBuilder;
import com.t3h.foodie.model.Category;
import com.t3h.foodie.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, CategoryAdapter.CategoryItemListener {
    private TextView tvSeeCategory, tvSeeProduct;
    private RecyclerView rcCategory, rcBestSelling;
    private BestSellingAdapter bestSellingAdapter;
    private Activity activity;
    private ViewFlipper viewFlipper;
    private CategoryAdapter categoryAdapter;
    private int idCategory = 0;

    int images[] = {R.drawable.bg_sdile, R.drawable.bg_sdile1, R.drawable.bg_sdile3, R.drawable.bg_sdile4, R.drawable.bg_sdile5};

    private FoodFragment foodFragment = new FoodFragment();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //sdile image
        viewFlipper = getActivity().findViewById(R.id.v_flipper);
        for (int image : images) {
            flipperImages(image);
        }
        activity = getActivity();
        //category
        rcCategory = activity.findViewById(R.id.rc_category);
        rcCategory.addItemDecoration(new SpacesItemDecoration(30));
        categoryAdapter = new CategoryAdapter(activity.getLayoutInflater());
        categoryAdapter.setListener(this);
        rcCategory.setAdapter(categoryAdapter);

        //Product Best selling
        rcBestSelling = activity.findViewById(R.id.rc_best_selling);
        rcBestSelling.addItemDecoration(new SpacesItemDecoration(30));
        bestSellingAdapter = new BestSellingAdapter(activity.getLayoutInflater());
        rcBestSelling.setAdapter(bestSellingAdapter);


        tvSeeCategory = getActivity().findViewById(R.id.tv_see_all_category);
        tvSeeCategory.setOnClickListener(this);
        tvSeeProduct = getActivity().findViewById(R.id.tv_see_all_product);
        tvSeeProduct.setOnClickListener(this);
        initDataCategory();
        initDataProductBest();
//        Category category = categoryAdapter.getData().get(0);
//        foodFragment.setCategory(category);
//        showFrament(foodFragment);
    }

    private void initDataProductBest() {
        ApiBuilder.getInstance().getBestSelling().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> data = response.body();
                bestSellingAdapter.setData(data);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e(HomeFragment.class.getName(), t.toString());
            }
        });
    }

    private void initDataCategory() {
        ApiBuilder.getInstance().getListCategorys().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> data = response.body();
                categoryAdapter.setData(data);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e(HomeFragment.class.getName(), t.toString());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_see_all_product:
                FragmentTransaction frProduct = getFragmentManager().beginTransaction();
                frProduct.replace(R.id.frame_container, new ProductFragment());
                frProduct.commit();
                break;
        }
    }

    private void showFrament(Fragment fragment) {
        FragmentTransaction frProduct = getFragmentManager().beginTransaction();
        frProduct.replace(R.id.frame_product, fragment);
        frProduct.commit();
    }


    @Override
    public void onCategoryItemClicked(Category category) {
        for (Category category1 : categoryAdapter.getData()) {
            category1.setSelected(false);
        }
        category.setSelected(true);
        categoryAdapter.notifyDataSetChanged();

        foodFragment.setCategory(category);
        showFrament(foodFragment);
    }


    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private final int mSpace;

        public SpacesItemDecoration(int mSpace) {
            this.mSpace = mSpace;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.right = mSpace;
            if (parent.getChildAdapterPosition(view) == 0)
                outRect.left = mSpace;
        }
    }

    public void flipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
    }
}
