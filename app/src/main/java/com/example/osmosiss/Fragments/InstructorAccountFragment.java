package com.example.osmosiss.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.osmosiss.R;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.databinding.FragmentInstructorAccountBinding;
import com.google.firebase.auth.FirebaseAuth;

public class InstructorAccountFragment extends Fragment {

    private FragmentInstructorAccountBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentInstructorAccountBinding.inflate(getLayoutInflater(), container, false);

        mAuth = FirebaseAuth.getInstance();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
                getActivity().finish();
                Toast.makeText(getContext(), "SignOut Successful", Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}