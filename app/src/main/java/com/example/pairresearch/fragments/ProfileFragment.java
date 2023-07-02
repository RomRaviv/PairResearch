package com.example.pairresearch.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.adapters.ViewPagerAdapter;
import com.example.pairresearch.models.MediaUpload;
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

public class ProfileFragment extends Fragment {

    private User currentUser = App.getLoggedUser();

    private ApiService apiService = App.getApiService();

    private ViewPager2 viewPager;
    private ImageButton editButton;
    private TextView name, email, description, linkedinUrl, phoneNumber;
    private TextView studentUniversity, studentYearOfStudy, studentQualifications;
    private TextView researcherTitle, researcherInstitute, researcherLabDescription, researcherResearches;

    private LinearLayout linear_student_detail, linear_researcher_detail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initViews(view);
        populateViewPager();


        name.setText("Name: " + currentUser.getName());
        email.setText("Email: "+ currentUser.getEmail());
        description.setText("Description: " + currentUser.getDescription());
        phoneNumber.setText("Phone: " + currentUser.getPhoneNumber());

        if (currentUser.getLinkedin().isEmpty()) {
            linkedinUrl.setVisibility(View.INVISIBLE);
        } else {
            linkedinUrl.setText("LinkedIn: " + currentUser.getLinkedin());
        }


        //Student Profile page
        if (App.currentUserType.equals(UserType.Student)) {
            linear_student_detail.setVisibility(View.VISIBLE);
            linear_researcher_detail.setVisibility(View.INVISIBLE);

            Student student = (Student) currentUser;
            studentUniversity.setText("University: " + student.getUniversity());
            studentYearOfStudy.setText("Year Of Study: " + student.getYearOfStudy());
            studentQualifications.setText("Qualifications: " + String.join(", ", student.getQualifications().getOther()));


        }

        //Researcher Profile Page
        else {

            linear_student_detail.setVisibility(View.INVISIBLE);
            linear_researcher_detail.setVisibility(View.VISIBLE);

            Researcher researcher = (Researcher) currentUser;
            researcherTitle.setText("Title: " + researcher.getTitle());
            researcherInstitute.setText("Institute: " + researcher.getInstitute());
            researcherLabDescription.setText("Lab Description" + researcher.getLabDescription());
            researcherResearches.setText("Researches: " + String.join(", ", researcher.getResearchesNames()));

        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle your edit action here
            }
        });

        return view;
    }

    private void populateViewPager() {
        apiService.getUserMediaUploads(currentUser.getId()).enqueue(new Callback<List<MediaUpload>>() {
            @Override
            public void onResponse(Call<List<MediaUpload>> call, Response<List<MediaUpload>> response) {
                if (response.isSuccessful()) {

                    List<String> imageUrls = new ArrayList<>();
                    List<MediaUpload> mediaUploads = response.body();
                    for(MediaUpload media : mediaUploads){
                        imageUrls.add(media.getMediaSource());
                    }
                    // now set up the ViewPager
                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(), imageUrls);
                    viewPager.setAdapter(viewPagerAdapter);
                } else {
                }
            }
            @Override
            public void onFailure(Call<List<MediaUpload>> call, Throwable t) {
                // handle error
            }
        });
    }

    private void initViews(View view) {
        // Initialize your views here
        viewPager = view.findViewById(R.id.viewPager_profile);
        editButton = view.findViewById(R.id.edit_button);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        description = view.findViewById(R.id.description);
        linkedinUrl = view.findViewById(R.id.linkedin_url);
        phoneNumber = view.findViewById(R.id.phone_number);

        studentUniversity = view.findViewById(R.id.student_university);
        studentYearOfStudy = view.findViewById(R.id.student_yearOfStudy);
        studentQualifications = view.findViewById(R.id.student_qualifications);

        researcherTitle = view.findViewById(R.id.researcher_title);
        researcherInstitute = view.findViewById(R.id.researcher_institute);
        researcherLabDescription = view.findViewById(R.id.researcher_lab_description);
        researcherResearches = view.findViewById(R.id.researcher_researches);

        linear_researcher_detail = view.findViewById(R.id.linear_researcher_detail);
        linear_student_detail = view.findViewById(R.id.linear_student_detail);
    }

}