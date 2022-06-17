package com.example.osmosiss.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.osmosiss.Course.CourseActivity;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityVideoViewBinding;

public class VideoViewActivity extends AppCompatActivity {

    private ActivityVideoViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoViewBinding.inflate(getLayoutInflater());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.getRoot());

        Intent intent = getIntent();

        String url = intent.getStringExtra("url");

        Uri uri = Uri.parse(url);
        binding.videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        mediaController.setMediaPlayer(binding.videoView);

        binding.videoView.setMediaController(mediaController);

        binding.videoView.start();

    }

}