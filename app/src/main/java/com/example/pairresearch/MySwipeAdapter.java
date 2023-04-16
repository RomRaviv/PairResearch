package com.example.pairresearch;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pairresearch.models.Research;
import com.example.pairresearch.models.Student;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class MySwipeAdapter extends BaseAdapter implements SwipeFlingAdapterView.onFlingListener {
    private ArrayList<Student> studentList;
    private ArrayList<Research> researchList;
    private Context mContext;
    private UserType currentUserType;

    public MySwipeAdapter(Context context, ArrayList<Student> studentList, ArrayList<Research> researchList ) {
        mContext = context;
        this.studentList = studentList;
        this.researchList = researchList;
        currentUserType = App.currentUserType;
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
        LinearLayout linear_card_images = view.findViewById(R.id.linear_card_images);
        ScrollView scroll_view_description = view.findViewById(R.id.scroll_view_description);
        TextView txt_card_description = view.findViewById(R.id.txt_card_description);

        switch (currentUserType) {
            case Researcher:
                Student student = studentList.get(position);
                for(String url : student.getAllMediaSources()){
                    ImageView imageView = new ImageView(mContext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(view).load(url).into(imageView);
                    linear_card_images.addView(imageView);
                }
                txt_card_description.setText(student.getDescription());
                break;
            case Student:
                Research research = researchList.get(position);
                for(String url : research.getAllMediaSources()){
                    ImageView imageView = new ImageView(mContext);
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    Glide.with(view).load(url).into(imageView);
                        linear_card_images.addView(imageView);
                }
                txt_card_description.setText(research.getDescription());
                break;
            default:
                break;
        }


        Research research = researchList.get(position);
//        imageView.setImageResource(student.getM);
//        txt_card_description.setText(student.getName());

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

    @Override
    public void onLeftCardExit(Object dataObject) {
        // Handle left swipe
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        // Handle right swipe
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
