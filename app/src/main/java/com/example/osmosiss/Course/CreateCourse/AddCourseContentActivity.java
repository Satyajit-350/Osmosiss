package com.example.osmosiss.Course.CreateCourse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.osmosiss.Adapters.AdapterCourseContent;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityAddCourseContentBinding;

import java.util.ArrayList;
import java.util.List;

public class AddCourseContentActivity extends AppCompatActivity implements NewSectionActivity.ExampleDialogListener {

    private ActivityAddCourseContentBinding binding;
    private AdapterCourseContent adapterCourseContent;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAddCourseContentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String title = intent.getStringExtra("Title");

        binding.courseTitleTV.setText(title);

        list = new ArrayList<>();
        adapterCourseContent = new AdapterCourseContent(this,list);
        binding.recyclerViewCourseContent.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewCourseContent.setHasFixedSize(true);
        binding.recyclerViewCourseContent.setAdapter(adapterCourseContent);
        getData();
        adapterCourseContent.notifyDataSetChanged();

        binding.addSectionIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        NewSectionActivity dialog = new NewSectionActivity();
        dialog.show(getSupportFragmentManager(),"add items to course");
    }

    private void getData() {

        list.add("First Section");
//        list.add("Second Section");
//        list.add("Third Section");
//        list.add("Fourth Section");

    }

    @Override
    public void additem(String courseTitle) {
        list.add(courseTitle);
        adapterCourseContent.notifyDataSetChanged();
    }
}