package com.cspremersms.stactoverflowapp.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClint {

    public static final String BASE_URL= "http://api.stackexchange.com";
    private static Retrofit retrofit= null;

    public static Retrofit RetrofitClint(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
