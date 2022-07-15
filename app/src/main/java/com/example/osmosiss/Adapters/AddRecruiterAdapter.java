package com.example.osmosiss.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddRecruiterAdapter extends RecyclerView.Adapter<AddRecruiterAdapter.ViewHolder> {

    private Context context;
    private List<Users> addRecruiters;
    private ClickListener listener;

    public AddRecruiterAdapter(Context context, List<Users> addRecruiters, ClickListener listener) {
        this.context = context;
        this.addRecruiters = addRecruiters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recruiter_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Users recruiter = addRecruiters.get(position);

       // Picasso.get().load(recruiter.getProfile_pic()).placeholder(R.drawable.person1).into(holder.img);
        holder.recName.setText(recruiter.getUsername());

        //TODO further change it to profession
        holder.recProf.setText(recruiter.getEmail());

        //select the recruiters
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                return listener.onLongClick(holder.getAdapterPosition());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemView.setBackgroundResource(R.color.teal_200);
                listener.onclick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return addRecruiters.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView img;
        private TextView recName,recProf;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.recruiter_image);
            recName = itemView.findViewById(R.id.recruiter_name);
            recProf = itemView.findViewById(R.id.recruiter_profession);
        }
    }

    public void filteredList(List<Users> filteredList){
        addRecruiters = filteredList;
        notifyDataSetChanged();
    }

    public interface ClickListener{

        void onclick(int position);

        boolean onLongClick(int position);
    }
}
