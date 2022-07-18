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

public class NewExperienceSection extends AppCompatDialogFragment {

    private ExperienceDialogListener listener;

    private TextView titleTV,companyTv,startDtTv,endDtTV,descTV;
    private ImageView startImg,endImage;
    private String title,company,startDt,endDt,desc;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_new_experience,null);
        builder.setView(view)
                .setTitle("Add Experience")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                        title = titleTV.getText().toString();
                        company = companyTv.getText().toString();
                        startDt = startDtTv.getText().toString();
                        endDt = endDtTV.getText().toString();
                        desc = descTV.getText().toString();

                        if(title.isEmpty()||company.isEmpty()||startDt.isEmpty()||endDt.isEmpty()||desc.isEmpty()){
                            Toast.makeText(getContext(), "All Fields are Required", Toast.LENGTH_SHORT).show();
                        }else{
                            listener.addExperience(title,company,startDt,endDt,desc);
                        }
                    }
                });
        titleTV = view.findViewById(R.id.titleTV);
        companyTv = view.findViewById(R.id.Company_nameTV);
        startDtTv = view.findViewById(R.id.Start_dateTV);
        endDtTV = view.findViewById(R.id.End_dateTV);
        descTV = view.findViewById(R.id.descriptionTV);

        startImg = view.findViewById(R.id.calender_start);
        endImage = view.findViewById(R.id.calender_end);

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
                        startDtTv.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });

        endImage.setOnClickListener(new View.OnClickListener() {
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
            listener = (ExperienceDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement ExperienceDialogListener");
        }
    }

    //interface to send data from dialog box to activity
    public interface ExperienceDialogListener{

        void addExperience(String title,String companyName, String startDt, String endDt, String desc);
    }
}
