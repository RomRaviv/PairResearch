package com.example.pairresearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.models.MediaUpload;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySwipeAdapter extends BaseAdapter implements SwipeFlingAdapterView.onFlingListener {

    private ApiService apiService = App.getApiService();
    private User currentUser = App.getLoggedUser();
    private ArrayList<Student> studentList;

    private FragmentActivity fragmentActivity;
    private ArrayList<Research> researchList;
    private Context mContext;
    private UserType currentUserType;


    public MySwipeAdapter(Context context, FragmentActivity fragmentActivity, ArrayList<Student> studentList, ArrayList<Research> researchList) {
        mContext = context;
        this.studentList = studentList;
        this.researchList = researchList;
        currentUserType = App.currentUserType;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public int getCount() {
        switch (currentUserType) {
            case Researcher:
                return studentList.size();
            case Student:
                return researchList.size();
            default:
                return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        switch (currentUserType) {
            case Researcher:
                return studentList.get(position);
            case Student:
                return researchList.get(position);
            default:
                return 0;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for the swipe card view
        View view = LayoutInflater.from(mContext).inflate(R.layout.swipe_card_layout, parent, false);

        // Populate the view with data from the current item in the list
        populateViewPager(view, position);

        TextView txt_card_description = view.findViewById(R.id.txt_card_description);
        ImageButton btn_swipe_no = view.findViewById(R.id.btn_swipe_no);
        ImageButton btn_swipe_yes = view.findViewById(R.id.btn_swipe_yes);

        btn_swipe_no.setOnClickListener(view1 -> rejection());
        btn_swipe_yes.setOnClickListener(view1 -> acceptance());

        txt_card_description.setText(currentUser.getDescription());
        Research research = researchList.get(position);

        return view;
    }

    @Override
    public void removeFirstObjectInAdapter() {
        switch (currentUserType) {
            case Researcher:
                studentList.remove(0);
            case Student:
                researchList.remove(0);
            default:
                break;
        }
        notifyDataSetChanged();
    }

    private void populateViewPager(View view, int position) {
        ViewPager2 viewPager = view.findViewById(R.id.viewPager_swipe_card);

        switch (currentUserType) {
            case Researcher:
                Student student = studentList.get(position);
                apiService.getMediaUploads(student.getId()).enqueue(new Callback<List<MediaUpload>>() {
                    @Override
                    public void onResponse(Call<List<MediaUpload>> call, Response<List<MediaUpload>> response) {
                        if (response.isSuccessful()) {
                            List<String> imageUrls = new ArrayList<>();
                            List<MediaUpload> mediaUploads = response.body();
                            for (MediaUpload media : mediaUploads) {
                                imageUrls.add(media.getMediaSource());
                            }

                            // now set up the ViewPager
                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fragmentActivity, imageUrls);
                            viewPager.setAdapter(viewPagerAdapter);
                        } else {
                            // handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MediaUpload>> call, Throwable t) {
                        // handle error
                    }
                });
                break;
            case Student:
                Research research = researchList.get(position);
                apiService.getMediaUploads(research.getId()).enqueue(new Callback<List<MediaUpload>>() {
                    @Override
                    public void onResponse(Call<List<MediaUpload>> call, Response<List<MediaUpload>> response) {
                        if (response.isSuccessful()) {
                            List<String> imageUrls = new ArrayList<>();
                            List<MediaUpload> mediaUploads = response.body();
                            for (MediaUpload media : mediaUploads) {
                                imageUrls.add(media.getMediaSource());
                            }

                            // now set up the ViewPager
                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(fragmentActivity, imageUrls);
                            viewPager.setAdapter(viewPagerAdapter);
                        } else {
                            // handle error
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MediaUpload>> call, Throwable t) {
                        // handle error
                    }
                });
                break;
            default:
                break;
        }

    }

    private void rejection() {

    }

    private void acceptance() {

    }


    @Override
    public void onLeftCardExit(Object dataObject) {
        // Handle left swipe
        //post to database there was a rejection
        rejection();
    }


    @Override
    public void onRightCardExit(Object dataObject) {
        // Handle right swipe
        //post to database there was an acceptance
        acceptance();
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        // Handle when there are no more items to swipe
    }

    @Override
    public void onScroll(float scrollProgressPercent) {
        // Handle scrolling of the cards
    }
}
