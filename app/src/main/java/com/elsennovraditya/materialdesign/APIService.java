package com.elsennovraditya.materialdesign;

import retrofit.RestAdapter;

/**
 * Created by elsen on 4/8/15.
 */
public class APIService {

    public RetrofitService build() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://private-434d3-elsen.apiary-mock.com")
                .build();
        return restAdapter.create(RetrofitService.class);
    }

}
