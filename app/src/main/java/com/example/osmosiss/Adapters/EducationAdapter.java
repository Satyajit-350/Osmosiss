package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.EducationModel;
import com.example.osmosiss.R;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {

    private Context context;
    private List<EducationModel> educationList;

    public EducationAdapter(Context context, List<EducationModel> educationList) {
        this.context = context;
        this.educationList = educationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EducationModel educationModel = educationList.get(position);
        holder.instituteTV.setText(educationModel.getInstituteName());
        holder.degreeTV.setText(educationModel.getDegree());
        holder.startTV.setText(educationModel.getStartDate());
        holder.endTV.setText(educationModel.getEndDate());
    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView instituteTV,degreeTV,startTV,endTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            instituteTV = itemView.findViewById(R.id.institute_name1);
            degreeTV = itemView.findViewById(R.id.education_detail1);
            startTV = itemView.findViewById(R.id.year_detail1);
            endTV = itemView.findViewById(R.id.year_detail2);

        }
    }
}
