package com.example.pairresearch.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.adapters.MySwipeAdapter;
import com.example.pairresearch.adapters.ViewPagerAdapter;
import com.example.pairresearch.models.MediaUpload;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Researcher;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SwipeFragment extends Fragment {
    private ApiService apiService = App.getApiService();
    private User currentUser = App.getLoggedUser();
    private UserType currentUserType = App.currentUserType;

    private SwipeFlingAdapterView swipeFlingAdapterView;
    private TextView textViewNoMatches;
    private MySwipeAdapter mySwipeAdapter;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Research> researchList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_swipe, container, false);

        swipeFlingAdapterView = view.findViewById(R.id.swipe_view);
        textViewNoMatches = view.findViewById(R.id.textViewNoMatches);

        initCardLists();

        // Initialize your adapter
        mySwipeAdapter = new MySwipeAdapter(getContext(), getActivity(), studentList, researchList);

        // Set adapter on the SwipeFlingAdapterView
        swipeFlingAdapterView.setAdapter(mySwipeAdapter);

        return view;
    }

    private void initCardLists() {

        switch (currentUserType) {
            case Researcher:
                Researcher researcher = (Researcher) currentUser;
                apiService.getStudentsCards(researcher.getActiveResearch().getId()).enqueue(new Callback<List<Student>>() {
                    @Override
                    public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                        if (response.isSuccessful()) {
                            List<Student> studentCards = response.body();
                            studentList.addAll(studentCards);
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Student>> call, Throwable t) {
                        Log.d("pttt","ApiService Failed: initCardLists; Research");
                    }
                });
                break;
            case Student:
                Student student = (Student) currentUser;
                apiService.getResearchesCards(student.getId()).enqueue(new Callback<List<Research>>() {
                    @Override
                    public void onResponse(Call<List<Research>> call, Response<List<Research>> response) {
                        if (response.isSuccessful()) {
                            List<Research> researchCards = response.body();
                            researchList.addAll(researchCards);
                        } else {
                            // handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Research>> call, Throwable t) {
                        Log.d("pttt","ApiService Failed: initCardLists; Student");
                    }
                });
                break;
            default:
                break;
        }
    }

}