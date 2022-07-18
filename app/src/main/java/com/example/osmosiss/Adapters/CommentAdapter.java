package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.Comment;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private Context context;
    private List<Comment> commentList;

    public CommentAdapter(Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.comment_txt.setText(comment.getCommentBody());
        String time = TimeAgo.using(comment.getCommentedAt());
        holder.comment_time.setText(time);

        FirebaseDatabase.getInstance().getReference()
                .child("Users").child(comment.getCommentedBy())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        //set profile picture
                        Picasso.get().load(users.getProfile_pic()).placeholder(R.drawable.user_img).into(holder.comment_pic);
                        holder.commented_person_name.setText(users.getUsername());
                        holder.commented_person_prof.setText(users.getProfession());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView comment_pic;
        private TextView commented_person_name,commented_person_prof;
        private TextView comment_time;
        private TextView comment_txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            comment_pic = itemView.findViewById(R.id.comment_profile_pic);
            commented_person_name = itemView.findViewById(R.id.commented_by);
            commented_person_prof = itemView.findViewById(R.id.commented_user_profession);
            comment_time = itemView.findViewById(R.id.comment_time);
            comment_txt = itemView.findViewById(R.id.comment_data);

        }
    }

}
