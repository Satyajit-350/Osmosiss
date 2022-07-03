package com.example.osmosiss.Course.CreateCourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityCoursePriceSetBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursePriceSetActivity extends AppCompatActivity {

    private ActivityCoursePriceSetBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCoursePriceSetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();

        String titlename = i.getStringExtra("title_course");

        binding.saveCoursePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pricePref = getSharedPreferences("PriceCourse",MODE_PRIVATE);
                SharedPreferences.Editor editor3 = pricePref.edit();
                editor3.putString("price_str",binding.editTextPrice.getText().toString());
                editor3.apply();
                Intent intent = new Intent(CoursePriceSetActivity.this,LaunchCourseActivity.class);
                intent.putExtra("t_name",titlename);
                intent.putExtra("price",binding.editTextPrice.getText().toString());
                startActivity(intent);
            }
        });
    }

//    private void getdata() {
//         SharedPreferences sharedPreferences = getSharedPreferences("NewCourse",MODE_PRIVATE);
//         Type type = new TypeToken<HashMap<String, String>>(){}.getType();
//         Gson gson = new Gson();
//         String json = sharedPreferences.getString("dataNewCourse",null);
//         HashMap<String, String> testHashMap2 = gson.fromJson(json, type);
//
//         //use values
//          String toastString = testHashMap2.get("CourseTitle") + " | " + testHashMap2.get("CourseLanguage");
//          Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();
//    }

//    private void getdata() {
//        SharedPreferences coursePref = getSharedPreferences("Course content",MODE_PRIVATE);
//        Gson gson = new Gson();
//        String json = coursePref.getString("course content list",null);
//        //in place of string we can give our own model class
//        Type type = new TypeToken<ArrayList<CourseContent>>() {}.getType();
//        example = gson.fromJson(json,type);
//
//        if(example == null){
//            Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
//            example = new ArrayList<>();
//        }
//
//        for(int i=0;i<example.size();i++){
//            Log.d("TAG",example.get(i).toString());
//            Toast.makeText(this, example.get(i).getTitle(), Toast.LENGTH_SHORT).show();
//        }
//    }
}