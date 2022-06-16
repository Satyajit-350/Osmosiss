package com.example.osmosiss.Fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osmosiss.Adapters.PostAdapter;
import com.example.osmosiss.Models.Post;
import com.example.osmosiss.databinding.FragmentFeaturedBinding;
import com.example.osmosiss.search.SearchActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FeaturedFragment extends Fragment {

    private FragmentFeaturedBinding binding;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding =  FragmentFeaturedBinding.inflate(getLayoutInflater(), container, false);

        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                startActivity(intent,b);
            }
        });

        postList = new ArrayList<>();
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(getContext(), postList);
        binding.categoryRv.setAdapter(postAdapter);

        if(postList.isEmpty()){
            binding.emptyDetail.setVisibility(View.VISIBLE);
        }else{
            binding.emptyDetail.setVisibility(View.INVISIBLE);
        }

//        getdata();
        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    binding.emptyDetail.setVisibility(View.INVISIBLE);
                    Post post = dataSnapshot.getValue(Post.class);
                    postList.add(post);
                }
                postAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return binding.getRoot();
    }
    //we will update these data while using firebase
//    private void getdata() {
//        categoryList.clear();
//        categoryList.add(new Category("122","Marketing","17","https://img.freepik.com/free-vector/gradient-ui-ux-background_23-2149051555.jpg?w=2000"));
//        //categoryList.add(new Category("124","UI/UX","12","https://sitegalleria.com/wp-content/uploads/2019/07/UI-UX-Design-Training-Bangalore-Site-Galleria.jpg"));
//        categoryList.add(new Category("123","Development","23","https://img.freepik.com/free-vector/programer-learning-programming-languages-by-computer-laptop-website-tutorial-channel-online-education-class-vector-illustration-software-development-programming-languages-learning_1150-55428.jpg?w=2000"));
//        categoryList.add(new Category("121","Programming","56","https://img.freepik.com/free-vector/programming-languages-learning-software-coding-courses-website-development-class-script-writing-it-programmers-cartoon-characters_335657-789.jpg?w=2000"));
//        categoryList.add(new Category("1223","Business","7","https://thumbs.dreamstime.com/b/business-training-courses-concept-modern-vector-illustration-flat-style-landing-page-mobile-app-poster-banner-flyer-189016731.jpg"));
//        categoryList.add(new Category("129","Photography","9","https://img.freepik.com/free-vector/online-course-photography-video_82574-6141.jpg?w=2000"));
//        categoryList.add(new Category("127","Music","36","https://img.freepik.com/free-vector/music-club-school-online-service-platform-students-learn_277904-17030.jpg?w=2000"));
//        categoryList.add(new Category("122","Marketing","17","https://img.freepik.com/free-vector/gradient-ui-ux-background_23-2149051555.jpg?w=2000"));
//        categoryList.add(new Category("124","UI/UX","12","https://sitegalleria.com/wp-content/uploads/2019/07/UI-UX-Design-Training-Bangalore-Site-Galleria.jpg"));
//        categoryList.add(new Category("123","Development","23","https://img.freepik.com/free-vector/programer-learning-programming-languages-by-computer-laptop-website-tutorial-channel-online-education-class-vector-illustration-software-development-programming-languages-learning_1150-55428.jpg?w=2000"));
//        categoryList.add(new Category("121","Programming","56","https://img.freepik.com/free-vector/programming-languages-learning-software-coding-courses-website-development-class-script-writing-it-programmers-cartoon-characters_335657-789.jpg?w=2000"));
//        categoryList.add(new Category("1223","Business","7","https://thumbs.dreamstime.com/b/business-training-courses-concept-modern-vector-illustration-flat-style-landing-page-mobile-app-poster-banner-flyer-189016731.jpg"));
//        categoryList.add(new Category("129","Photography","9","https://img.freepik.com/free-vector/online-course-photography-video_82574-6141.jpg?w=2000"));
//        categoryList.add(new Category("127","Music","36","https://img.freepik.com/free-vector/music-club-school-online-service-platform-students-learn_277904-17030.jpg?w=2000"));
//    }
}