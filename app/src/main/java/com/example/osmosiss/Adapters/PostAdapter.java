package com.example.osmosiss.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Comment.CommentActivity;
import com.example.osmosiss.Models.Post;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = postList.get(position);

        Picasso.get().load(post.getCoursePic()).placeholder(R.drawable.business).into(holder.imageView);
        holder.courseTitle.setText(post.getCourseTitle());
        holder.courseDescription.setText(post.getCourseDesc());
        holder.likeText.setText(post.getPostLike()+"");
        holder.commentTxt.setText(post.getCommentCount()+"");
        FirebaseDatabase.getInstance().getReference().child("Users")
                .child(post.getPostedBy()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Users users = snapshot.getValue(Users.class);
                        holder.postedByName.setText(users.getUsername());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        FirebaseDatabase.getInstance().getReference()
                        .child("Posts")
                        .child(post.getPostId())
                        .child("likes")
                        .child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        holder.likeText.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.heart_red,0,0);
                                    }else{
                                        holder.likeText.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                FirebaseDatabase.getInstance().getReference().child("Posts")
                                                        .child(post.getPostId())
                                                        .child("likes")
                                                        .child(FirebaseAuth.getInstance().getUid())
                                                        .setValue(true).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void unused) {
                                                                FirebaseDatabase.getInstance().getReference()
                                                                        .child("Posts")
                                                                        .child(post.getPostId())
                                                                        .child("postLike")
                                                                        .setValue(post.getPostLike()+1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void aVoid) {
                                                                                holder.likeText.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.heart_red,0,0);
                                                                            }
                                                                        });
                                                            }
                                                        });
                                            }
                                        });
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

        holder.commentTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommentActivity.class);
                intent.putExtra("postId",post.getPostId());
                intent.putExtra("postedBy",post.getPostedBy());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        private TextView postedByName;
        private ImageView imageView;
        private TextView courseTitle;
        private TextView courseDescription;
        private TextView likeText,commentTxt,shareTxt;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postedByName = itemView.findViewById(R.id.posted_by);
            imageView = itemView.findViewById(R.id.course_pic);
            courseTitle = itemView.findViewById(R.id.course_user_name);
            courseDescription = itemView.findViewById(R.id.course_desc);
            likeText = itemView.findViewById(R.id.LikeIV);
            commentTxt = itemView.findViewById(R.id.CommentIV);
            shareTxt = itemView.findViewById(R.id.ShareIV);

        }
    }
}
