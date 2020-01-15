package com.devproject.fagundezdev.recyclerviewimages;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomUserService {

    @GET("/api?results=10")
    Call<ArrayList<Friend>> getFriends();
}
