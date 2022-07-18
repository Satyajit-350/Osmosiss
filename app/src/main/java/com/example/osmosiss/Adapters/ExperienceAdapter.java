package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.ExperienceModel;
import com.example.osmosiss.R;

import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    private Context context;
    private List<ExperienceModel> experienceList;

    public ExperienceAdapter(Context context, List<ExperienceModel> experienceList) {
        this.context = context;
        this.experienceList = experienceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExperienceModel experienceModel = experienceList.get(position);
        holder.name.setText(experienceModel.getTitle());
        holder.companyName.setText(experienceModel.getCompanyName());
        holder.startDt.setText(experienceModel.getStartDate());
        holder.endDt.setText(experienceModel.getEndDate());
        holder.detail.setText(experienceModel.getDetails());
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,companyName,startDt,endDt,detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.experience_title1);
            companyName = itemView.findViewById(R.id.experience_placeTV1);
            startDt = itemView.findViewById(R.id.dates_experince1);
            endDt = itemView.findViewById(R.id.dates_experince2);
            detail = itemView.findViewById(R.id.details_exp1);
        }
    }
}
