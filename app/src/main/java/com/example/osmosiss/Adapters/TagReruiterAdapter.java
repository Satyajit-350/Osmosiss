package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.TagRecruiter;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TagReruiterAdapter extends RecyclerView.Adapter<TagReruiterAdapter.ViewHolder> {

    private Context context;
    private List<Users> tagRecruiters;

    public TagReruiterAdapter(Context context, List<Users> tagRecruiters) {
        this.context = context;
        this.tagRecruiters = tagRecruiters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users recruiter = tagRecruiters.get(position);
        //Picasso.get().load(recruiter.getProfile_pic()).placeholder(R.drawable.ic_person).into(holder.imageView);
        holder.nameText.setText(recruiter.getUsername());
    }

    @Override
    public int getItemCount() {
        return tagRecruiters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView nameText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.recruiter_pic);
            nameText = itemView.findViewById(R.id.tag_recruiter_name);
        }
    }
}
