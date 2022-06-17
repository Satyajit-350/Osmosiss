package com.example.osmosiss.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.CourseLessonDetail;
import com.example.osmosiss.R;
import com.example.osmosiss.UI.VideoViewActivity;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private Context context;
    private List<CourseLessonDetail> courseLessonList;

    public LessonAdapter(Context context, List<CourseLessonDetail> courseLessonList) {
        this.context = context;
        this.courseLessonList = courseLessonList;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_detail_items,parent,false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        CourseLessonDetail lessonDetail = courseLessonList.get(position);
        holder.lessonText.setText(lessonDetail.getName());
        holder.lessonVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoViewActivity.class);
                intent.putExtra("url",lessonDetail.getVideoUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseLessonList.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder{

        private ImageView lessonVideo;
        private TextView lessonText;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);

            lessonText = itemView.findViewById(R.id.lesson_name);
            lessonVideo = itemView.findViewById(R.id.lesson_video_img);
        }
    }
}
