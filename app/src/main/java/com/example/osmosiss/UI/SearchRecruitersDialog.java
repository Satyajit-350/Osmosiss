package com.example.osmosiss.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osmosiss.Adapters.AddRecruiterAdapter;
import com.example.osmosiss.Course.CreateCourse.NewSectionActivity;
import com.example.osmosiss.Models.TagRecruiter;
import com.example.osmosiss.Models.TopSearch;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchRecruitersDialog extends AppCompatDialogFragment implements AddRecruiterAdapter.ClickListener{

    private SearchView searchEditText;
    private RecyclerView listRecyclerView;
    private List<Users> recruitersArrayList;
    private AddRecruiterAdapter adapter;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private RecruiterDialogListener listener;

    boolean isSelected = false;
    private List<Users> selectedRecruiters = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.bottom_layout,null);
        builder.setView(view)
                .setTitle("Tag Recruiters")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.addRecruiter(selectedRecruiters);
                    }
                });

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        recruitersArrayList = new ArrayList<>();;
        searchEditText = view.findViewById(R.id.editTextTextRecruiterName);
        listRecyclerView = view.findViewById(R.id.recruiter_recycler_view_list);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AddRecruiterAdapter(getContext(),recruitersArrayList,this);
        listRecyclerView.setAdapter(adapter);

        getdata();

        searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        return builder.create();
    }

    private void filter(String newText) {
        List<Users> myList = new ArrayList<>();
        for(Users item: recruitersArrayList){
            //TODO change it to profession
            if(item.getUsername().toLowerCase().contains(newText.toLowerCase())){
                myList.add(item);
            }
        }
        adapter.filteredList(myList);
    }

    private void getdata() {
        database.getReference().child("Users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        recruitersArrayList.clear();
                        for(DataSnapshot snapshot1: snapshot.getChildren()){
                            Users users = snapshot1.getValue(Users.class);
                            if(users.getIsAdmin()==2){
                                recruitersArrayList.add(users);
                            }

                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    @Override
    public void onclick(int pos) {
        selectedRecruiters.add(recruitersArrayList.get(pos));
        if(selectedRecruiters.size()==0){
            isSelected = false;
        }
    }

    @Override
    public boolean onLongClick(int position) {
        selectedRecruiters.remove(position);
        return true;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (RecruiterDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement RecruiterDialogListener");
        }
    }

    public interface RecruiterDialogListener{
        //return the list of recruiters
        void addRecruiter(List<Users> recruiters);
    }
}
