package com.example.osmosiss.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.osmosiss.MainActivity;
import com.example.osmosiss.R;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.databinding.FragmentAccountBinding;
import com.google.firebase.auth.FirebaseAuth;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private FirebaseAuth mAuth;

    public AccountFragment(){
        //empty fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentAccountBinding.inflate(getLayoutInflater(), container, false);

        mAuth = FirebaseAuth.getInstance();

        binding.personalinfo.setVisibility(View.VISIBLE);
        binding.experience.setVisibility(View.GONE);
        binding.review.setVisibility(View.GONE);

        binding.textView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getContext(), "SignOut Successful", Toast.LENGTH_SHORT).show();
            }
        });

        binding.personalinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.personalinfo.setVisibility(View.VISIBLE);
                binding.experience.setVisibility(View.GONE);
                binding.review.setVisibility(View.GONE);
                binding.personalinfobtn.setTextColor(getResources().getColor(R.color.blue));
                binding.experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                binding.reviewbtn.setTextColor(getResources().getColor(R.color.grey));
            }
        });

        binding.experiencebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.personalinfo.setVisibility(View.GONE);
                binding.experience.setVisibility(View.VISIBLE);
                binding.review.setVisibility(View.GONE);
                binding.personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                binding.experiencebtn.setTextColor(getResources().getColor(R.color.blue));
                binding.reviewbtn.setTextColor(getResources().getColor(R.color.grey));
            }
        });

        binding.reviewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.personalinfo.setVisibility(View.GONE);
                binding.experience.setVisibility(View.GONE);
                binding.review.setVisibility(View.VISIBLE);
                binding.personalinfobtn.setTextColor(getResources().getColor(R.color.grey));
                binding.experiencebtn.setTextColor(getResources().getColor(R.color.grey));
                binding.reviewbtn.setTextColor(getResources().getColor(R.color.blue));
            }
        });

        return binding.getRoot();
    }
}