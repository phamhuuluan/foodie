package com.t3h.foodie.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private static Api api;

    public static Api getInstance() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        api = new Retrofit.Builder()
                .baseUrl("http://192.168.1.184/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api.class);
        return api;
    }

}
