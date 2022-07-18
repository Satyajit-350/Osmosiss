package com.example.osmosiss.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.osmosiss.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class NewEducationSection extends AppCompatDialogFragment {

    private EducationDialogListener listener;

    private TextView instituteNameTV,degreeTV,startDtTV,endDtTV,gradeTV;
    private ImageView startImg,endEmg;

    private String instituteName,degree,startDt,endDt,grade;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_new_education,null);
        builder.setView(view)
                .setTitle("Add Education")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                       instituteName = instituteNameTV.getText().toString();
                       degree = degreeTV.getText().toString();
                       startDt = startDtTV.getText().toString();
                       endDt = endDtTV.getText().toString();
                       grade = gradeTV.getText().toString();

                       if(instituteName.isEmpty()||degree.isEmpty()||startDt.isEmpty()||endDt.isEmpty()||grade.isEmpty()){
                           Toast.makeText(getContext(), "Required Fields", Toast.LENGTH_SHORT).show();
                       }else{
                           listener.addEducation(instituteName,degree,startDt,endDt,grade);
                       }
                    }
                });

        instituteNameTV = view.findViewById(R.id.school_nameTV);
        degreeTV = view.findViewById(R.id.Degree_nameTV);
        startDtTV = view.findViewById(R.id.Start_dateTV);
        endDtTV = view.findViewById(R.id.End_dateTV);
        gradeTV = view.findViewById(R.id.Grade_TV);

        startImg = view.findViewById(R.id.startImg);
        endEmg = view.findViewById(R.id.endImg);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Start Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        MaterialDatePicker materialDatePicker2 = MaterialDatePicker.Builder.datePicker()
                .setTitleText("End Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        startImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open calender
                materialDatePicker.show(getChildFragmentManager(),"tag_picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startDtTV.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });
        endEmg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open calender
                materialDatePicker2.show(getParentFragmentManager(),"tag_picker2");
                materialDatePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endDtTV.setText(materialDatePicker2.getHeaderText());
                    }
                });
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EducationDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement SkillsDialogListener");
        }
    }

    //interface to send data from dialog box to activity
    public interface EducationDialogListener{

        void addEducation(String instituteName,String degree,String startDt,String endDt,String grade);
    }

}
