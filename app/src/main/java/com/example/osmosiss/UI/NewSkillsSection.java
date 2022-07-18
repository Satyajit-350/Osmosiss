package com.example.osmosiss.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.osmosiss.R;

public class NewSkillsSection extends AppCompatDialogFragment {

    private TextView skillTV;
    private String skill;

    private SkillsDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_new_skills,null);
        builder.setView(view)
                .setTitle("Add Skills")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                        skill = skillTV.getText().toString();
                        if(skillTV.getText().toString().trim().equalsIgnoreCase("")){
                            skillTV.setError("Required");
                        }else{
                            listener.addSkills(skill);
                        }
                    }
                });

        skillTV = view.findViewById(R.id.titleTV);

        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SkillsDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement SkillsDialogListener");
        }
    }

    //interface to send data from dialog box to activity
    public interface SkillsDialogListener{

        void addSkills(String skillTitle);
    }
}
