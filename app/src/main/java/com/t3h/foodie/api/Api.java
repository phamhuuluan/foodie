package com.t3h.foodie.api;

import com.t3h.foodie.model.Category;
import com.t3h.foodie.model.Product;

import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface Api {
    @Headers("Content-Type: application/json")

    @GET("foodie/category.php")
    Call<List<Category>> getListCategorys();

    @GET("foodie/product.php")
    Call<List<Product>> getListProduct();

    @GET("foodie/getProductBest.php")
    Call<List<Product>> getBestSelling();

    @GET("foodie/getFood.php")
    Call<List<Product>> getFood(@QueryMap Map<String, Integer> map);
}
