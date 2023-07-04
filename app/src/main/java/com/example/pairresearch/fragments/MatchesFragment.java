package com.example.pairresearch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.adapters.MatchItemAdapter;
import com.example.pairresearch.models.MatchItem;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Researcher;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends Fragment {

    private ApiService apiService = App.getApiService();
    private User currentUser = App.getLoggedUser();
    private UserType currentUserType = App.currentUserType;
    private ListView matchListView;
    private ArrayList<MatchItem> matchItems;
    private MatchItemAdapter matchAdapter;

    public MatchesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        matchListView = view.findViewById(R.id.match_list);

        // Initialize match items
        matchItems = new ArrayList<>();
        matchAdapter = new MatchItemAdapter(getContext(), matchItems);
        matchListView.setAdapter(matchAdapter);

        init();

        return view;
    }


    private void init() {
        switch (currentUserType){
            case Student:
                Student student = (Student) currentUser;
                initMatchItemsResearches(student.getResearchMatches());

                break;

            case Researcher:
                Research research = ((Researcher)currentUser).getActiveResearch();
                initMatchItemsStudents(research.getStudentMatches());


                break;
            default:
                break;

        }
    }

    private void initMatchItemsStudents(ArrayList<Student> students) {
        for(Student student : students) {
            MatchItem matchItem = new MatchItem(student.getName(), "", student.getEmail(), student.getPhoneNumber(),student.getUniversity() ,student.getYearOfStudy(), "");
            matchItems.add(matchItem);
            matchAdapter.notifyDataSetChanged();
        }
    }

    private void initMatchItemsResearches(List<Research> researches) {
        for(Research research : researches){
            apiService.getResearcher(research.getResearcherId()).enqueue(new Callback<Researcher>() {
                @Override
                public void onResponse(Call<Researcher> call, Response<Researcher> response) {
                    Researcher researcher = response.body();
                    MatchItem matchItem = new MatchItem(researcher.getName(),researcher.getInstitute(),researcher.getEmail(), researcher.getPhoneNumber(), "","",research.getName());
                    matchItems.add(matchItem);
                    matchAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<Researcher> call, Throwable t) {

                }
            });
        }
    }

}
