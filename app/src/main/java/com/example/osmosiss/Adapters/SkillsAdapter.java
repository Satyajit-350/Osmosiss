package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.R;

import java.util.List;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

    private Context context;
    private List<String> skillsList;

    public SkillsAdapter(Context context, List<String> skillsList) {
        this.context = context;
        this.skillsList = skillsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.skillsTV.setText(skillsList.get(position));
    }

    @Override
    public int getItemCount() {
        return skillsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView skillsTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            skillsTV = itemView.findViewById(R.id.skills_tv);
        }
    }
}
