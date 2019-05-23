package com.example.main.webservice;

import com.example.main.interfaces.IFriendListRepository;
import com.example.main.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FriendListService {
    //TODO:
    @POST("login/addFriend")
    Call<String> addFriend(@Body User user, @Query("friendEmail") String mail);

    @POST("login/getAllFriends")
    Call<List<User>> getAllFriends(@Body User currentUser);

    @POST("login/getFriend/{mail}")
    Call<User> getSpecificFriend(@Path("friendEmail") String mail);
}
