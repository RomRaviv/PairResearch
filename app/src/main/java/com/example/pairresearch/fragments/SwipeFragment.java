package com.example.pairresearch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pairresearch.R;
import com.example.pairresearch.adapters.MySwipeAdapter;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Student;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class SwipeFragment extends Fragment {
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

        // Initialize your adapter
        mySwipeAdapter = new MySwipeAdapter(getContext(), getActivity(), studentList, researchList);

        // Set adapter on the SwipeFlingAdapterView
        swipeFlingAdapterView.setAdapter(mySwipeAdapter);

        // Set FlingListener on the SwipeFlingAdapterView
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this could also be delegated to the adapter
                mySwipeAdapter.removeFirstObjectInAdapter();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                // Handle left card exit
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                // Handle right card exit
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Handle adapter about to be empty
            }

            @Override
            public void onScroll(float v) {
                // Handle scroll
            }
        });

        return view;
    }

    // TODO: Implement methods to handle swipe events and update the view accordingly
}