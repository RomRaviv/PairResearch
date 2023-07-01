package com.example.pairresearch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pairresearch.R;
import com.example.pairresearch.fragments.ImageFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private List<String> imageUrls;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<String> imageUrls) {
        super(fragmentActivity);
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return ImageFragment.newInstance(imageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}

