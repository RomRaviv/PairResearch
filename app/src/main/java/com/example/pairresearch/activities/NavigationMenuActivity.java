package com.example.pairresearch.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.pairresearch.R;
import com.example.pairresearch.fragments.MatchesFragment;
import com.example.pairresearch.fragments.ProfileFragment;
import com.example.pairresearch.fragments.SwipeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NavigationMenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        frameLayout = findViewById(R.id.frame_layout);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_item_matches:
                    // load home fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MatchesFragment()).commit();
                    return true;
                case R.id.menu_item_profile:
                    // load profile fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ProfileFragment()).commit();
                    return true;
                case R.id.menu_item_swipe:
                    // load settings fragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new SwipeFragment()).commit();
                    return true;
                default:
                    return false;
            }
        });
    }
}
