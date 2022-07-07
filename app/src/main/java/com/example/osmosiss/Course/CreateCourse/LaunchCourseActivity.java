package com.example.osmosiss.Course.CreateCourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Fragments.InstructorHomeFragment;
import com.example.osmosiss.Fragments.LearningFragment;
import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.Models.Post;
import com.example.osmosiss.databinding.ActivityLaunchCourseBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class LaunchCourseActivity extends AppCompatActivity {

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

    private int uploads = 0;

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

    }

    private void getAllData() {

        SharedPreferences sharedPreferences = getSharedPreferences("NewCourse",MODE_PRIVATE);
        Type type = new TypeToken<HashMap<String, String>>(){}.getType();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dataNewCourse",null);
        HashMap<String, String> testHashMap2 = gson.fromJson(json, type);

        //use values
//        String toastString = testHashMap2.get("CourseTitle") + " | " + testHashMap2.get("CourseLanguage");
//        Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();

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
//        //upload videos
//        dialog.show();
//        for(uploads=0;uploads<courseVideoUri.size();uploads++){
//
//            reference1.putFile(courseVideoUri.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                   reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                       @Override
//                       public void onSuccess(Uri uri) {
//                           String url = String.valueOf(uri);
//                           SendVideos(url);
//                       }
//                   });
//                }
//            });
//        }
//        //uploads pdfs
//        dialog.show();
//        for(uploads=0;uploads<coursePdfUri.size();uploads++){
//
//            reference2.putFile(coursePdfUri.get(uploads)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    reference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            String url = String.valueOf(uri);
//                            SendPdf(url);
//                        }
//                    });
//                }
//            });
//        }
//        //uploads course content titles
//        dialog.show();
//        database.getReference().child("Posts")
//                .child("course Topics")
//                .setValue(courseContentTopic).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            dialog.dismiss();
//                            Toast.makeText(LaunchCourseActivity.this, "All data loaded", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

    }

    private void SendPdf(String url) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pdf", url);
        database.getReference().child("Posts")
                .push()
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        coursePdfUri.clear();
                        Toast.makeText(LaunchCourseActivity.this, "Videos uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void SendVideos(String url) {
        HashMap<String, String> hashMap = new HashMap<>();
        int i = 0;
        hashMap.put("video"+i, url);
        database.getReference().child("Posts")
                .push()
                .setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        dialog.dismiss();
                        courseVideoUri.clear();
                        Toast.makeText(LaunchCourseActivity.this, "Videos uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}