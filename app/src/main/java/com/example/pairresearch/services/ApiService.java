package com.example.pairresearch.services;

import com.example.pairresearch.models.MediaUpload;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Researcher;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface  ApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("researchers")
    Call<List<Researcher>> getResearchers();

    @GET("researches")
    Call<List<Research>> getResearches();

    @GET("matches")
    Call<String> getMatches();

    @GET("students")
    Call<List<Student>> getStudents();

    @GET("students/{id}")
    Call<Student> getStudent(@Path("id") int id);

    @GET("researches/{id}")
    Call<Research> getResearch(@Path("id") int id);

    @GET("researchers/{id}")
    Call<Researcher> getResearcher(@Path("id") int id);

    @FormUrlEncoded
    @POST("login")
    Call<User> login(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("uploadResearch")
    Call<ResponseBody> uploadResearch(
            @Part("researchName") RequestBody researchName,
            @Part("degree") RequestBody degree,
            @Part("researchPayment") RequestBody researchPayment,
            @Part("researchDescription") RequestBody researchDescription,
            @Part("isFinalProject") RequestBody isFinalProject,
            @Part List<MultipartBody.Part> files
    );

    @GET("mediaUploads/{userId}")
    Call<List<MediaUpload>> getMediaUploads(@Path("userId") int userId);

    @GET("researchMediaUploads/{researchId}")
    Call<List<MediaUpload>> getResearchMediaUploads(@Path("userId") int researchId);


}
