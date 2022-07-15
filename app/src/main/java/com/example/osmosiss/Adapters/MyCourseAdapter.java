package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.Post;
import com.example.osmosiss.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCourseAdapter extends RecyclerView.Adapter<MyCourseAdapter.ViewHolder> {

    private Context context;
    private List<Post> myCourseList;

    public MyCourseAdapter(Context context, List<Post> myCourseList) {
        this.context = context;
        this.myCourseList = myCourseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_course_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = myCourseList.get(position);
        holder.cTitle.setText(post.getCourseTitle());
        holder.cDesc.setText(post.getCourseDesc());
        holder.Language.setText(post.getCourseLanguage());

        Picasso.get().load(post.getCoursePic()).placeholder(R.drawable.business).into(holder.cPic);
    }

    @Override
    public int getItemCount() {
        return myCourseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cPic;
        TextView cTitle,cDesc,Language;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cPic = itemView.findViewById(R.id.my_course_img);
            cTitle = itemView.findViewById(R.id.myCourse_title);
            cDesc = itemView.findViewById(R.id.myCourse_desc);
            Language = itemView.findViewById(R.id.textView_language);
        }
    }
}
