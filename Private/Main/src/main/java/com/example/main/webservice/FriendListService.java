package com.example.main.webservice;

import com.example.main.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FriendListService {
    //TODO:
    @POST("/addFriend/{id}")
    Call<String> addFriend(@Body User user, @Path("id") String id);

    @POST()
    Call<List<User>> getAllFriends(@Body User currentUser);

    @GET("/specificFriend/{id}")
    Call<User> getSpecificFriend(@Path("id") String id);
}
