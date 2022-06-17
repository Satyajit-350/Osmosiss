package com.example.osmosiss.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Course.CourseActivity;
import com.example.osmosiss.Models.CourseItems;
import com.example.osmosiss.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularCourseAdapter extends RecyclerView.Adapter<PopularCourseAdapter.PopularCourseViewHolder> {

    private Context context;
    private List<CourseItems> courseItemsList;

    public PopularCourseAdapter(Context context, List<CourseItems> courseItemsList) {
        this.context = context;
        this.courseItemsList = courseItemsList;
    }

    @NonNull
    @Override
    public PopularCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular,parent,false);
        return new PopularCourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularCourseViewHolder holder, int position) {

        CourseItems courseItems = courseItemsList.get(position);

        holder.courseName.setText(courseItems.getCourseName());
        Picasso.get().load(courseItems.getCoursePic()).placeholder(R.drawable.hacking_course).into(holder.courseImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CourseActivity.class);
                intent.putExtra("Cname",courseItems.getCourseName());
                intent.putExtra("image",courseItems.getCoursePic());
//                Pair[] pairs = new Pair[1];
//                pairs[0] = new Pair<View,String>(holder.courseImg,"image");
//                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);
//                context.startActivity(intent,activityOptions);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseItemsList.size();
    }

    public class PopularCourseViewHolder extends RecyclerView.ViewHolder{

        private TextView courseName;
        private ImageView courseImg;

        public PopularCourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseImg = itemView.findViewById(R.id.popular_course_img);
            courseName = itemView.findViewById(R.id.PopularCourse_text);
        }
    }
}
