package com.example.osmosiss.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Models.TopSearch;
import com.example.osmosiss.R;

import java.util.List;

public class TopSearchAdapter extends RecyclerView.Adapter<TopSearchAdapter.TopSearchViewHolder> {

    private Context context;
    private List<TopSearch> topSearchesList;

    public TopSearchAdapter(Context context, List<TopSearch> topSearchesList) {
        this.context = context;
        this.topSearchesList = topSearchesList;
    }

    @NonNull
    @Override
    public TopSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_search_items,parent,false);
        return new TopSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSearchViewHolder holder, int position) {

        TopSearch topSearch = topSearchesList.get(position);

        holder.topSearchTxt.setText(topSearch.getNames());

    }

    @Override
    public int getItemCount() {
        return topSearchesList.size();
    }

    public class TopSearchViewHolder extends RecyclerView.ViewHolder{

        private TextView topSearchTxt;

        public TopSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            topSearchTxt = itemView.findViewById(R.id.top_search_txt);
        }


    }
}
