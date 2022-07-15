package com.example.osmosiss.Course.CreateCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Adapters.TagReruiterAdapter;
import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.Models.Post;
import com.example.osmosiss.Models.TagRecruiter;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.UI.SearchRecruitersDialog;
import com.example.osmosiss.databinding.ActivityLaunchCourseBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class LaunchCourseActivity extends AppCompatActivity implements SearchRecruitersDialog.RecruiterDialogListener{

    private ActivityLaunchCourseBinding binding;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private FirebaseAuth mAuth;
    private ProgressDialog dialog;
    private List<CourseContent> example;
    private String cPrice;
    private String cTitle;
    private String cSubTitle;
    private String cDesc;
    private String cCategory;
    private String cSubCategory;
    private String cLanguage;
    private String cLevel;
    private String cPic;

    private Uri imgUri;
    private List<Uri> courseVideoUri,coursePdfUri;
    private List<String> courseContentTopic;

    private List<Users> taggedRecruiters;
    private TagReruiterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLaunchCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String courseName= intent.getStringExtra("t_name");
        String priceCourse = intent.getStringExtra("price");


        binding.priceCourse.setText(priceCourse);

        example = new ArrayList<>();
        courseVideoUri = new ArrayList<>();
        coursePdfUri = new ArrayList<>();
        courseContentTopic = new ArrayList<>();

        taggedRecruiters = new ArrayList<>();

        database =FirebaseDatabase.getInstance();
        mAuth =FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Post Uploading");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        getAllData();

        binding.textView8.setText(cTitle);

        binding.uploadWholeCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();
            }
        });

        binding.addRecruiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchRecruitersDialog dialog = new SearchRecruitersDialog();
                dialog.show(getSupportFragmentManager(),"add Recruiters");
            }
        });

        binding.tagRecyclerViewRecruiter.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.tagRecyclerViewRecruiter.setHasFixedSize(true);
        adapter = new TagReruiterAdapter(this,taggedRecruiters);
        binding.tagRecyclerViewRecruiter.setAdapter(adapter);
    }

    private void getAllData() {

        SharedPreferences sharedPreferences = getSharedPreferences("NewCourse",MODE_PRIVATE);
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataNewCourse",null);
        HashMap<String, String> testHashMap2 = gson.fromJson(json, type);


        cTitle = testHashMap2.get("CourseTitle");
        cSubTitle = testHashMap2.get("CourseSubTitle");
        cDesc = testHashMap2.get("CourseDescription");
        cCategory = testHashMap2.get("CourseCategory");
        cSubCategory = testHashMap2.get("CourseSubCategory");
        cLanguage = testHashMap2.get("CourseLanguage");
        cLevel = testHashMap2.get("CourseLevel");
        //convert it into uri
        cPic = testHashMap2.get("CoursePic");

        imgUri = Uri.parse(cPic);

        SharedPreferences coursePref = getSharedPreferences("Course content",MODE_PRIVATE);
        String json2 = coursePref.getString("course content list",null);
        //in place of string we can give our own model class
        Type type2 = new TypeToken<ArrayList<CourseContent>>() {}.getType();
        example = gson.fromJson(json2,type2);

        if(example == null){
            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
            example = new ArrayList<>();
        }

        //example arrayList contains all the course content data i.e. the videos and pdfs
        for(int i=0;i<example.size();i++){
            courseVideoUri.add(Uri.parse(example.get(i).getVideoUri()));
            coursePdfUri.add(Uri.parse(example.get(i).getPdfUri()));
            courseContentTopic.add(example.get(i).getTitle());
        }

        SharedPreferences pricePref = getSharedPreferences("PriceCourse",MODE_PRIVATE);
        cPrice = pricePref.getString("price_str",null);

    }

    private void upload() {

        dialog.show();
        final StorageReference reference = storage.getReference().child("posts")
                .child("coursePictures/")
                .child(mAuth.getUid())
                .child(new Date().getTime()+"");

        reference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Post post = new Post();
                        post.setCoursePic(uri.toString());
                        post.setPostedBy(mAuth.getUid());
                        post.setCourseTitle(cTitle);
                        post.setCourseSubTitle(cSubTitle);
                        post.setCourseDesc(cDesc);
                        post.setCourseCategory(cCategory);
                        post.setCourseSubCategory(cSubCategory);
                        post.setCourseLanguage(cLanguage);
                        post.setCourseLevel(cLevel);
                        post.setPostedAt(new Date().getTime());
                        post.setCourseContentList(example);
                        post.setRecruiters(taggedRecruiters);
                        post.setCoursePrice(cPrice);
                        database.getReference().child("Posts")
                                .push()
                                .setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(LaunchCourseActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        Intent intent = new Intent(LaunchCourseActivity.this, InstructorActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });
                    }
                });

            }
        });

    }

    @Override
    public void addRecruiter(List<Users> recruiters) {
        for(Users data: recruiters){
            taggedRecruiters.add(data);
        }
        adapter.notifyDataSetChanged();
    }
}