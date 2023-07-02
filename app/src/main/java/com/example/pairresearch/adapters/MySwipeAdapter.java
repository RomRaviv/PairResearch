package com.example.pairresearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.models.MediaUpload;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Researcher;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserAction;
import com.example.pairresearch.models.enums.UserType;
import com.example.pairresearch.services.ApiService;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
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

        switch (currentUserType) {
            case Researcher:
                Student  student = studentList.get(position);
                txt_card_description.setText(student.getDescription());
                btn_swipe_no.setOnClickListener(view1 -> rejection(currentUser.getId(), student.getId(),UserType.Student));
                btn_swipe_yes.setOnClickListener(view1 -> acceptance(currentUser.getId(), student.getId(),UserType.Student));

            case Student:
                Research research = researchList.get(position);
                txt_card_description.setText(research.getDescription());
                btn_swipe_no.setOnClickListener(view1 -> rejection(currentUser.getId(), research.getId(),UserType.Research));
                btn_swipe_yes.setOnClickListener(view1 -> acceptance(currentUser.getId(), research.getId(),UserType.Research));
            default:
                break;
        }

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
                apiService.getUserMediaUploads(student.getId()).enqueue(new Callback<List<MediaUpload>>() {
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
                apiService.getResearchMediaUploads(research.getId()).enqueue(new Callback<List<MediaUpload>>() {
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

    private void rejection(int swiperId, int sweptId, UserType swiperType) {
        apiService.postUserAction(swiperId, sweptId, swiperType.ordinal(), UserAction.Rejected.ordinal()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // handle success
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
            }
        });
    }

    private void acceptance(int swiperId, int sweptId, UserType swiperType ) {
        apiService.postUserAction(swiperId, sweptId,swiperType.ordinal(), UserAction.Accepted.ordinal()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // handle success
                //Check for a possible match
                switch (currentUserType) {
                    case Student:
                        apiService.checkMatch(swiperId, sweptId).enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.body()) { // if the response is true
                                    showMatchPopup();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                        break;
                    case Researcher:
                        apiService.checkMatch(sweptId,swiperId).enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                if (response.body()) { // if the response is true
                                    showMatchPopup();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {

                            }
                        });
                        break;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // handle failure
            }
        });
    }
    @Override
    public void onLeftCardExit(Object dataObject) {
        if (dataObject instanceof Student) {
            Student student = (Student) dataObject;
            Researcher researcher = (Researcher) currentUser;
            rejection(researcher.getActiveResearch().getId(), student.getId(), UserType.Student);
            // Handle student swipe rejection
        } else if (dataObject instanceof Research) {
            Research research = (Research) dataObject;
            // Handle research swipe rejection
            rejection(currentUser.getId(), research.getId(), UserType.Research);
        }
    }


    @Override
    public void onRightCardExit(Object dataObject) {
        if (dataObject instanceof Student) {
            // Handle student swipe rejection
            Student student = (Student) dataObject;
            Researcher researcher = (Researcher) currentUser;
            acceptance(researcher.getActiveResearch().getId(), student.getId(),UserType.Student);
        } else if (dataObject instanceof Research) {
            // Handle research swipe rejection
            Research research = (Research) dataObject;
            acceptance(currentUser.getId(), research.getId(),UserType.Research);
        }
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        // Handle when there are no more items to swipe
    }

    @Override
    public void onScroll(float scrollProgressPercent) {
        // Handle scrolling of the cards
    }


    private void showMatchPopup() {
        new AlertDialog.Builder(mContext)
                .setTitle("Match Found!")
                .setMessage("You have a new match.")
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }


}
