package com.devproject.fagundezdev.recyclerviewimages;

import com.devproject.fagundezdev.recyclerviewimages.model.Example;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RandomUserService {

    @GET("/api?results=20")
    Call<Example> getFriends();
}
