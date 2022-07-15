package com.example.osmosiss.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.CourseContent;
import com.example.osmosiss.R;
import com.example.osmosiss.UI.VideoViewActivity;

import java.util.List;

public class AdapterCourseContent extends RecyclerView.Adapter<AdapterCourseContent.ViewHolder> {

    private Context context;
    private List<CourseContent> list;

    public AdapterCourseContent(Context context, List<CourseContent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseContent courseContent = list.get(position);
        holder.textViewTitle.setText(courseContent.getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoViewActivity.class);
                intent.putExtra("video",courseContent.getVideoUri());
                context.startActivity(intent);
            }
        });
        holder.contentNumber.setText("0"+Integer.parseInt(String.valueOf(position+1)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTitle,contentNumber;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.content_title);
            contentNumber = itemView.findViewById(R.id.content_number);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }
}
