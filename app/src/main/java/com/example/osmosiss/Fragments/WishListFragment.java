package com.example.osmosiss.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osmosiss.R;
import com.example.osmosiss.databinding.FragmentWishListBinding;

import java.util.ArrayList;
import java.util.List;

public class WishListFragment extends Fragment {

    private FragmentWishListBinding binding;
    private List<String> strings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentWishListBinding.inflate(getLayoutInflater(), container, false);

        strings = new ArrayList<>();

        binding.myLearningsRv.setVisibility(View.VISIBLE);
        binding.wishEmptyView.setVisibility(View.INVISIBLE);
        binding.wishEmptyViewTxt.setVisibility(View.INVISIBLE);

        if(strings.size()==0){
            binding.myLearningsRv.setVisibility(View.INVISIBLE);
            binding.wishEmptyView.setVisibility(View.VISIBLE);
            binding.wishEmptyViewTxt.setVisibility(View.VISIBLE);
        }
        return binding.getRoot();
    }
}