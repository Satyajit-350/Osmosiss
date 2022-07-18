package com.example.osmosiss.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.ProjectModel;
import com.example.osmosiss.R;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private Context context;
    private List<ProjectModel> projectList;

    public ProjectAdapter(Context context, List<ProjectModel> projectList) {
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projects_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProjectModel projectModel = projectList.get(position);
        holder.projectTitle.setText(projectModel.getProjectTitle());
        holder.startDT.setText(projectModel.getStartDate());
        holder.endDT.setText(projectModel.getEndDate());
        holder.projectDesc.setText(projectModel.getProjectDetail());

        holder.githubLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open link
                String url = projectModel.getGithubLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView projectTitle,githubLink,startDT,endDT,projectDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            projectTitle = itemView.findViewById(R.id.project_name1);
            githubLink = itemView.findViewById(R.id.link_project1);
            startDT = itemView.findViewById(R.id.date_project1);
            endDT = itemView.findViewById(R.id.date_project2);
            projectDesc = itemView.findViewById(R.id.project_detail1);
        }
    }
}
