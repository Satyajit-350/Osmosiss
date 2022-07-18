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

public class NewProjectSection extends AppCompatDialogFragment {

    private TextView projectTitleTV,githubLinkTV,detailsTV,startTV,endTV;
    private ImageView calenderStartDT,calenderEndDt;

    private ProjectDialogListener listener;

    private String title,link,detail,startDt,endDt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_new_project,null);
        builder.setView(view)
                .setTitle("Add Project")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                        title = projectTitleTV.getText().toString();
                        link = githubLinkTV.getText().toString();
                        detail = detailsTV.getText().toString();
                        startDt = startTV.getText().toString();
                        endDt = endTV.getText().toString();

                        if(title!=null||link!=null||detail!=null||startDt!=null||endDt!=null){
                            listener.addProject(title,link,detail,startDt,endDt);
                        }else{
                            Toast.makeText(getContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        projectTitleTV = view.findViewById(R.id.project_titleTV);
        githubLinkTV = view.findViewById(R.id.Github_Link);
        detailsTV = view.findViewById(R.id.description_TV);
        startTV = view.findViewById(R.id.Start_dateTV);
        endTV = view.findViewById(R.id.End_dateTV);

        calenderStartDT = view.findViewById(R.id.startCalender);
        calenderEndDt = view.findViewById(R.id.endCalender);

        MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Start Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        MaterialDatePicker materialDatePicker2 = MaterialDatePicker.Builder.datePicker()
                .setTitleText("End Date").setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build();

        calenderStartDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open calender
                materialDatePicker.show(getChildFragmentManager(),"tag_picker");
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        startTV.setText(materialDatePicker.getHeaderText());
                    }
                });
            }
        });

        calenderEndDt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open calender
                materialDatePicker2.show(getParentFragmentManager(),"tag_picker2");
                materialDatePicker2.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        endTV.setText(materialDatePicker2.getHeaderText());
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
            listener = (ProjectDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement ProjectDialogListener");
        }
    }

    //interface to send data from dialog box to activity
    public interface ProjectDialogListener{

        void addProject(String projectTitle,String githubLink, String details, String startDt, String endDt);
    }
}
