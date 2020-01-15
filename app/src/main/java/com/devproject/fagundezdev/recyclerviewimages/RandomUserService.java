package com.devproject.fagundezdev.recyclerviewimages;

import com.devproject.fagundezdev.recyclerviewimages.model.Example;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/*
 * Interface name: RandomUserService
 * Interface used by retrofit library
 * @author Miguel Fagundez
 * @version 1.0
 * @since January 2020
 * */
public interface RandomUserService {

    // I will get 20 user profiles
    @GET("/api?results=20")
    Call<Example> getFriends();
}
