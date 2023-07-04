package com.example.pairresearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pairresearch.App;
import com.example.pairresearch.R;
import com.example.pairresearch.models.MatchItem;
import com.example.pairresearch.models.User;
import com.example.pairresearch.models.enums.UserType;

import java.util.ArrayList;

public class MatchItemAdapter extends BaseAdapter {
    private User currentUser = App.getLoggedUser();
    private UserType currentUserType = App.currentUserType;
    private Context context;
    private ArrayList<MatchItem> matchItems;

    public MatchItemAdapter(Context context, ArrayList<MatchItem> matchItems) {
        this.context = context;
        this.matchItems = matchItems;
    }

    @Override
    public int getCount() {
        return matchItems.size();
    }

    @Override
    public Object getItem(int position) {
        return matchItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.match_item, null);
        }

        // Get the current match item
        MatchItem matchItem = matchItems.get(position);

        // Populate the view with data
        TextView matchInfoTextView = view.findViewById(R.id.match_info);
        String matchInfo = "";
        switch (currentUserType){
            case Student:
                matchInfo = matchItem.getName() + "\n" +
                        "Research: " + matchItem.getResearchName() + "\n" +
                        "Institute: " + matchItem.getInstitute() + "\n\n" +
                        "Email: " + matchItem.getEmail() + "\n" +
                        "Phone: " + matchItem.getPhone();
                break;
            case Research:
                matchInfo = matchItem.getName() + "\n" +
                        "Year of Study: " + matchItem.getYearOfStudy() + "\n" +
                        "University: " + matchItem.getUniversity() + "\n\n" +
                        "Email: " + matchItem.getEmail() + "\n" +
                        "Phone: " + matchItem.getPhone();
                break;
            default:
                break;
        }

        matchInfoTextView.setText(matchInfo);

        return view;
    }
}

