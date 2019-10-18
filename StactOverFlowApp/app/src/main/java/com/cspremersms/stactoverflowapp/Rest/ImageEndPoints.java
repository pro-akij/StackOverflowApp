package com.cspremersms.stactoverflowapp.Rest;

import com.cspremersms.stactoverflowapp.model.ImageModel;
import com.cspremersms.stactoverflowapp.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ImageEndPoints {

    @GET("/2.2/users?page=1&pagesize=5&order=desc&site=stackoverflow")
    Call<ImageModel> getImage();

}
