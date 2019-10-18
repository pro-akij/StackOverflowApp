package com.cspremersms.stactoverflowapp.Rest;

import com.cspremersms.stactoverflowapp.model.User;
import com.cspremersms.stactoverflowapp.model.UserReceive;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserEndPoints {

    @GET("/2.2/users?page=1&pagesize=5&order=desc&site=stackoverflow")
    Call<UserReceive> getUser(@Query("sort") String sort);
}
