package com.example.osmosiss.Course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.osmosiss.Adapters.LessonAdapter;
import com.example.osmosiss.Models.CourseLessonDetail;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityCourseBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private ActivityCourseBinding binding;
    private LessonAdapter lessonAdapter;
    private List<CourseLessonDetail> lessonArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent i = getIntent();

        String pic = i.getStringExtra("image");
        String courseName = i.getStringExtra("Cname");
        binding.customToolbar.setTitleTextColor(this.getColor(R.color.black));
        binding.customToolbar.setTitle(courseName);

        Picasso.get().load(pic).placeholder(R.drawable.hacking_course).into(binding.courseImage);

        lessonArrayList = new ArrayList<>();

        binding.courseDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.courseDetailRecyclerView.setHasFixedSize(true);

        lessonAdapter = new LessonAdapter(this,lessonArrayList);
        binding.courseDetailRecyclerView.setAdapter(lessonAdapter);

        getLessonDetails();

        lessonAdapter.notifyDataSetChanged();
    }

    private void getLessonDetails() {
        lessonArrayList.add(new CourseLessonDetail("Welcome To The Course","https://vod-progressive.akamaized.net/exp=1655498873~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3911%2F15%2F394559823%2F1675380435.mp4~hmac=1ba4a740269af52ea214ff0e92e351c77f307cad4f057624bcb5ec5c87909061/vimeo-prod-skyfire-std-us/01/3911/15/394559823/1675380435.mp4?filename=production+ID%3A3826739.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Introduction  ","https://player.vimeo.com/external/374119382.sd.mp4?s=2e2cf9aa0bd93c53987df1a2b07240602942ce72&profile_id=165&oauth2_token_id=57447761"));
        lessonArrayList.add(new CourseLessonDetail("Installation and setUp","https://vod-progressive.akamaized.net/exp=1655498830~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3573%2F13%2F342867287%2F1371800785.mp4~hmac=5d137ad66e63c891139d2e9a990ff37b5a4c4c0d0bbb4ae0ce0fd5e8192d58a4/vimeo-prod-skyfire-std-us/01/3573/13/342867287/1371800785.mp4?filename=Pexels+Videos+2516159.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Set up of Visual Studio code","https://vod-progressive.akamaized.net/exp=1655499245~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2329%2F20%2F511649324%2F2362949623.mp4~hmac=5187bf6a3c1f7843eaaeeaa6a315c0b223144f17331d11c74feb225daa952287/vimeo-prod-skyfire-std-us/01/2329/20/511649324/2362949623.mp4?filename=pexels-tea-oebel-6804117.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Creating our first project","https://vod-progressive.akamaized.net/exp=1655499021~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2927%2F28%2F714639462%2F3313309715.mp4~hmac=f8f8fadd6da10bf85fc9c13da3f7f4775edea898a2c7b4d103ea427d58e84abe/vimeo-prod-skyfire-std-us/01/2927/28/714639462/3313309715.mp4?filename=file.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Links and Resources","https://vod-progressive.akamaized.net/exp=1655499348~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F73%2F17%2F425366493%2F1842515723.mp4~hmac=8d5367208e9b4bd1a16ed144ad1cd10b0da9f72310a9f7525253e1105327acb7/vimeo-prod-skyfire-std-us/01/73/17/425366493/1842515723.mp4?filename=production+ID%3A4549682.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Intro to Git and Github","https://vod-progressive.akamaized.net/exp=1655499348~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F73%2F17%2F425366493%2F1842515723.mp4~hmac=8d5367208e9b4bd1a16ed144ad1cd10b0da9f72310a9f7525253e1105327acb7/vimeo-prod-skyfire-std-us/01/73/17/425366493/1842515723.mp4?filename=production+ID%3A4549682.mp4"));
        lessonArrayList.add(new CourseLessonDetail("Publishing your Project","https://vod-progressive.akamaized.net/exp=1655499489~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2690%2F17%2F438451071%2F1914366984.mp4~hmac=80971cbe02e392d3e2356e0394b2811d61739227515f5a4fb2f4aae353688b6a/vimeo-prod-skyfire-std-us/01/2690/17/438451071/1914366984.mp4?filename=production+ID%3A4873122.mp4"));
    }
}