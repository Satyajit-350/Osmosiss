package com.example.osmosiss.Course.CourseDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Adapters.AdapterCourseContent;
import com.example.osmosiss.Adapters.LessonAdapter;
import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.Models.Post;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityCourseDetailBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    private ActivityCourseDetailBinding binding;
    private List<CourseContent> arrayList,dataList;
    private AdapterCourseContent adapter;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    String postId,postedBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayList = new ArrayList<>();
        dataList = new ArrayList<>();
        Intent intent = getIntent();

        postId = intent.getStringExtra("postId");
        postedBy = intent.getStringExtra("postedBy");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.ratingBar.setRating(4.3f);

        binding.courseItemDetails.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.courseItemDetails.setHasFixedSize(true);
        adapter = new AdapterCourseContent(this,dataList);
        binding.courseItemDetails.setAdapter(adapter);

        database.getReference().child("Posts")
                .child(postId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Post post = snapshot.getValue(Post.class);
                        Picasso.get().load(post.getCoursePic()).placeholder(R.drawable.business).into(binding.imageView);
                        binding.textView5.setText(post.getCourseTitle());
                        binding.textView9.setText(post.getCourseDesc());
                        FirebaseDatabase.getInstance().getReference().child("Users")
                                .child(post.getPostedBy()).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        Users users = snapshot.getValue(Users.class);
                                        binding.textView15.setText(users.getUsername());
                                        binding.textView25.setText(users.getUsername());
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        binding.textView17.setText(post.getCourseLanguage());
                        binding.textView20.setText(post.getCoursePrice());
                        arrayList = post.getCourseContentList();
                        for(int j=0;j<arrayList.size();j++){
                            dataList.add(arrayList.get(j));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }
}