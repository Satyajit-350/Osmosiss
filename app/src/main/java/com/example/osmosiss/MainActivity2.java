package com.example.osmosiss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class MainActivity2 extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        videoView = findViewById(R.id.videoView2);

        Intent intent = getIntent();
        String uri  = intent.getStringExtra("video");

        videoView.setVideoPath(uri);
        videoView.start();
    }
}