package com.example.pairresearch.models;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DataManager {
    private static final String BASE_URL = "http://your-api-url.com/";

    private static DataManager instance;
    private final RequestQueue requestQueue;

    private DataManager(Context context) {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    public void getResearchers(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "researchers";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void getResearches(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "researches";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void getMatches(Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "matches";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void login(String email, String password, Response.Listener<String> responseListener, Response.ErrorListener errorListener) {
        String url = BASE_URL + "login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


    // Other methods for POST, PUT, DELETE requests
}
