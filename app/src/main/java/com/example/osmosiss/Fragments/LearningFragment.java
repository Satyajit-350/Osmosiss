package com.example.osmosiss.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osmosiss.R;
import com.example.osmosiss.databinding.FragmentLearningBinding;

import java.util.ArrayList;
import java.util.List;


public class LearningFragment extends Fragment {

    private FragmentLearningBinding binding;
    private List<String> strings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLearningBinding.inflate(getLayoutInflater(), container, false);

        strings = new ArrayList<>();

        binding.myLearningsRv.setVisibility(View.VISIBLE);
        binding.emptyView.setVisibility(View.INVISIBLE);
        binding.emptyViewTxt.setVisibility(View.INVISIBLE);

        if(strings.size()==0){
            binding.myLearningsRv.setVisibility(View.INVISIBLE);
            binding.emptyView.setVisibility(View.VISIBLE);
            binding.emptyViewTxt.setVisibility(View.VISIBLE);
        }

        return binding.getRoot();
    }
}