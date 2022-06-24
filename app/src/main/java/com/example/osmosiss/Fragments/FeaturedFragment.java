package com.example.osmosiss.Fragments;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osmosiss.Adapters.PostAdapter;
import com.example.osmosiss.MainActivity;
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

        binding = FragmentFeaturedBinding.inflate(getLayoutInflater(), container, false);

        postList = new ArrayList<>();
        binding.postRv.setLayoutManager(new LinearLayoutManager(getContext()));
        postAdapter = new PostAdapter(getContext(), postList);
        binding.postRv.setAdapter(postAdapter);

        if(checkInternet()!=false){
            binding.noInternetView.setVisibility(View.INVISIBLE);
            getdata();
        }else{
            binding.noInternetView.setVisibility(View.VISIBLE);
        }


        //swipeRefresh Layout
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                boolean checkConnection = checkInternet();
                if(checkConnection==false){
                    binding.noInternetView.setVisibility(View.VISIBLE);
                }else{
                    binding.noInternetView.setVisibility(View.INVISIBLE);
                    getdata();

                }
                binding.swipeRefreshLayout.setRefreshing(false);
            }
        });

        //search bar
        binding.searchET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                Bundle b = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
                startActivity(intent,b);
            }
        });


        return binding.getRoot();
    }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                // Internet Available
                return true;
            }else {
                //No internet
                return false;
            }
        } else {
            //No internet
            return false;
        }
    }

    //update these data while using firebase
    private void getdata() {
//        binding.shimmer.startShimmer();
//        binding.postRv.setVisibility(View.VISIBLE);
        postList.clear();
        database.getReference().child("Posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Post post = dataSnapshot.getValue(Post.class);
                    post.setPostId(dataSnapshot.getKey());
                    postList.add(post);
                }
//                binding.shimmer.stopShimmer();
//                binding.shimmer.setVisibility(View.GONE);
                postAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                binding.noInternetView.setVisibility(View.VISIBLE);
            }
        });

    }

}