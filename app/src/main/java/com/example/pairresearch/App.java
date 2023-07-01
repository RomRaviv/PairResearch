package com.example.pairresearch;

import android.app.Application;

import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    public static UserType currentUserType;

    private static ApiService apiService = null;

    private static User loggedUser;
    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://127.0.0.1:8080/")  // replace with your base URL
                .addConverterFactory(GsonConverterFactory.create())  // for JSON conversion
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static User getLoggedUser(){
        return loggedUser;
    }

    public static void setLoggedUser(User user){
        loggedUser = user;
    }


    public static ApiService getApiService() {
        return apiService;
    }


}
