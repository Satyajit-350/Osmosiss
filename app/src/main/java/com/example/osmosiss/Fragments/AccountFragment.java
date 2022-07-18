package com.example.osmosiss.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.osmosiss.Adapters.EducationAdapter;
import com.example.osmosiss.Adapters.ExperienceAdapter;
import com.example.osmosiss.Adapters.ProjectAdapter;
import com.example.osmosiss.Adapters.SkillsAdapter;
import com.example.osmosiss.MainActivity;
import com.example.osmosiss.Models.EducationModel;
import com.example.osmosiss.Models.ExperienceModel;
import com.example.osmosiss.Models.ProjectModel;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.UI.EditProfileActivity;
import com.example.osmosiss.databinding.FragmentAccountBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    private SkillsAdapter skillsAdapter;
    private List<String> skillsArrayList;

    private EducationAdapter educationAdapter;
    private List<EducationModel> educationArrayList;

    private ExperienceAdapter experienceAdapter;
    private List<ExperienceModel> experienceArrayList;

    private ProjectAdapter projectAdapter;
    private List<ProjectModel> projectArrayList;

    private List<String> dummySkillList;
    private List<EducationModel> dummyEducationList;
    private List<ExperienceModel> dummyExperienceList;
    private List<ProjectModel> dummyProjectList;

    public AccountFragment(){
        //empty fragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentAccountBinding.inflate(getLayoutInflater(), container, false);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        skillsArrayList = new ArrayList<>();
        educationArrayList = new ArrayList<>();
        experienceArrayList = new ArrayList<>();
        projectArrayList = new ArrayList<>();

        dummySkillList = new ArrayList<>();
        dummyEducationList = new ArrayList<>();
        dummyExperienceList = new ArrayList<>();
        dummyProjectList = new ArrayList<>();

        binding.personalinfo.setVisibility(View.VISIBLE);
        binding.experience.setVisibility(View.GONE);
        binding.review.setVisibility(View.GONE);

        database.getReference().child("Users").child(mAuth.getUid())
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Users users = snapshot.getValue(Users.class);
                                Picasso.get().load(users.getProfile_pic()).placeholder(R.drawable.user_img).into(binding.userProfilePic);
                                binding.userName.setText(users.getUsername());
                                binding.userProfession.setText(users.getProfession());
                                binding.aboutMe.setText(users.getAbout());
                                binding.userPhone.setText(users.getPhone());
                                binding.userMail.setText(users.getEmail());
                                binding.userLocation.setText(users.getCountry());

                                dummySkillList = users.getSkills();
                                if(dummySkillList!=null){
                                    for(int i=0;i<dummySkillList.size();i++){
                                        skillsArrayList.add(dummySkillList.get(i));
                                    }
                                    skillsAdapter.notifyDataSetChanged();
                                }
                                dummyEducationList = users.getEducation();
                                if(dummyEducationList!=null){
                                    for(int i=0;i<dummyEducationList.size();i++){
                                        educationArrayList.add(dummyEducationList.get(i));
                                    }
                                    educationAdapter.notifyDataSetChanged();
                                }
                                dummyExperienceList = users.getExperience();
                                if(dummyExperienceList!=null){
                                    for(int i=0;i<dummyExperienceList.size();i++){
                                        experienceArrayList.add(dummyExperienceList.get(i));
                                    }
                                    experienceAdapter.notifyDataSetChanged();
                                }
                                dummyProjectList = users.getProject();
                                if(dummyProjectList!=null) {
                                    for (int i = 0; i < dummyProjectList.size(); i++) {
                                        projectArrayList.add(dummyProjectList.get(i));
                                    }
                                    projectAdapter.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        //skills
        binding.profileSkillsRV.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.profileSkillsRV.setHasFixedSize(true);
        skillsAdapter = new SkillsAdapter(getContext(),skillsArrayList);
        binding.profileSkillsRV.setAdapter(skillsAdapter);

        //education
        binding.profileEducationRv.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.profileEducationRv.setHasFixedSize(true);
        educationAdapter = new EducationAdapter(getContext(),educationArrayList);
        binding.profileEducationRv.setAdapter(educationAdapter);

        binding.editButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        binding.editButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        binding.editButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        //experience
        binding.profileExperienceRv.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.profileExperienceRv.setHasFixedSize(true);
        experienceAdapter = new ExperienceAdapter(getContext(),experienceArrayList);
        binding.profileExperienceRv.setAdapter(experienceAdapter);

        //projects
        binding.profileProjectRv.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }

        });
        binding.profileProjectRv.setHasFixedSize(true);
        projectAdapter = new ProjectAdapter(getContext(),projectArrayList);
        binding.profileProjectRv.setAdapter(projectAdapter);

        binding.signOut.setOnClickListener(new View.OnClickListener() {
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
                binding.education.setVisibility(View.VISIBLE);
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
                binding.education.setVisibility(View.GONE);
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
                binding.education.setVisibility(View.GONE);
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