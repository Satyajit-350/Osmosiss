package com.example.osmosiss.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.osmosiss.Adapters.TopSearchAdapter;
import com.example.osmosiss.Models.TopSearch;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private TopSearchAdapter tsAdapter;
    private List<TopSearch> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        arrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.HORIZONTAL,false));
        tsAdapter = new TopSearchAdapter(SearchActivity.this,arrayList);
        binding.recyclerView.setAdapter(tsAdapter);
        getTopSearches();
        tsAdapter.notifyDataSetChanged();
    }

    //we will update it while using firebase
    private void getTopSearches() {
        arrayList.clear();
        arrayList.add(new TopSearch("Python"));
        arrayList.add(new TopSearch("Java"));
        arrayList.add(new TopSearch("C++"));
        arrayList.add(new TopSearch("Ruby"));
        arrayList.add(new TopSearch("HTML"));
        arrayList.add(new TopSearch("Android"));
        arrayList.add(new TopSearch("Web3"));
        arrayList.add(new TopSearch("C#"));
        arrayList.add(new TopSearch("Kotlin"));
        arrayList.add(new TopSearch("CSS"));
        arrayList.add(new TopSearch("Artificial Intelligence"));


    }
}