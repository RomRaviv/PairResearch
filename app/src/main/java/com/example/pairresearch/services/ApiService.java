package com.example.pairresearch.services;

import com.example.pairresearch.models.Match;
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
import retrofit2.http.Path;

import java.util.List;

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

    @GET("/media_upload_user/{userId}")
    Call<List<MediaUpload>> getUserMediaUploads(@Path("userId") int userId);

    @GET("research_media_uploads/{researchId}")
    Call<List<MediaUpload>> getResearchMediaUploads(@Path("userId") int researchId);

    @GET("match/{studentId}/{researchId}")
    Call<Boolean> checkMatch(@Path("studentId") int studentId, @Path("researchId") int researchId);

    @GET("student_matches/{id}")
    Call<List<Research>> getStudentMatches(@Path("id") int id);

    @GET("research_matches/{id}")
    Call<List<Student>> getResearchMatches(@Path("id") int id);

    @GET("get_researches_cards/{id}")
    Call<List<Research>> getResearchesCards(@Path("id") int id);

    @GET("get_students_cards/{id}")
    Call<List<Research>> getStudentsCards(@Path("id") int id);



    @FormUrlEncoded
    @POST("login")
    Call<Student> login_student(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login")
    Call<Researcher> login_researcher(@Field("email") String email, @Field("password") String password);

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


    @FormUrlEncoded
    @POST("userAction")
    Call<ResponseBody> postUserAction(
            @Field("swiperId") int swiperId,
            @Field("sweptId") int sweptId,
            @Field("SwiperType") int swiperType,
            @Field("actionType") int actionType
    );




}
