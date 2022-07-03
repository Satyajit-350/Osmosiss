package com.example.osmosiss.Course.CreateCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Adapters.AdapterCourseContent;
import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityAddCourseContentBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AddCourseContentActivity extends AppCompatActivity implements NewSectionActivity.ExampleDialogListener {

    private ActivityAddCourseContentBinding binding;
    private AdapterCourseContent adapterCourseContent;
    private List<CourseContent> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAddCourseContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getData();
        Intent intent = getIntent();

        String title = intent.getStringExtra("Title");

        binding.courseTitleTV.setText(title);

        list = new ArrayList<>();
        adapterCourseContent = new AdapterCourseContent(this,list);
        binding.recyclerViewCourseContent.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCourseContent.setHasFixedSize(true);
        binding.recyclerViewCourseContent.setAdapter(adapterCourseContent);

//        getData();
//        adapterCourseContent.notifyDataSetChanged();

        binding.addSectionIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        binding.NextTVAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences coursePref = getSharedPreferences("Course content",MODE_PRIVATE);
                SharedPreferences.Editor editor1 = coursePref.edit();
                Gson gson = new Gson();
                String json = gson.toJson(list);
                editor1.putString("course content list",json);
                editor1.apply();
                Toast.makeText(AddCourseContentActivity.this, "saved", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AddCourseContentActivity.this,CoursePriceSetActivity.class);
                intent.putExtra("title_course",binding.courseTitleTV.getText().toString());
                startActivity(intent1);
            }
        });
    }

    private void openDialog() {
        NewSectionActivity dialog = new NewSectionActivity();
        dialog.show(getSupportFragmentManager(),"add items to course");
    }

    private void getData() {
        SharedPreferences coursePref = getSharedPreferences("Course content",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = coursePref.getString("course content list",null);
        //in place of string we can give our own model class
        Type type = new TypeToken<ArrayList<CourseContent>>() {}.getType();
        list = gson.fromJson(json,type);

        if(list == null){
            list = new ArrayList<>();
        }


//        list.add("Second Section");
//        list.add("Third Section");
//        list.add("Fourth Section");

    }

    @Override
    public void additem(String courseTitle,String vUri,String pUri) {
        //have some edge case like if we do not add video only pdf is added it will crash due to null pointer exception
        //so we have to handle it later
        list.add(new CourseContent(courseTitle,vUri,pUri));
        Toast.makeText(this, courseTitle, Toast.LENGTH_SHORT).show();
        adapterCourseContent.notifyDataSetChanged();
    }
}