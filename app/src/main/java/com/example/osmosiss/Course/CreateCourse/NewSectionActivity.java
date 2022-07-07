package com.example.osmosiss.Course.CreateCourse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityNewSectionBinding;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class NewSectionActivity extends AppCompatDialogFragment {

    private EditText courseTitleEditText;
    private Button getVideoBtn;
    private Button getPdfBtn;
    private VideoView videoView;
    private PDFView pdfView;
    private Uri videoUri,pdfUri;
    private ExampleDialogListener listener;
    private FirebaseStorage storage;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    private ProgressDialog dialog;

    private String Vuri;
    private String pdfuri;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_new_section,null);
        builder.setView(view)
                .setTitle("Add Section")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                        String courseTitle = courseTitleEditText.getText().toString();
//                        Vuri = videoUri.toString();
//                        String pdfuri = pdfUri.toString();
                        //also add pdf
                        listener.additem(courseTitle,Vuri,pdfuri);
                    }
                });

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(getContext());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Video Uploading");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        courseTitleEditText = view.findViewById(R.id.section_NameTV);
        getVideoBtn = view.findViewById(R.id.addVideo_btn);
        getPdfBtn = view.findViewById(R.id.add_articleBtn);
        videoView = view.findViewById(R.id.video_View);
        pdfView = view.findViewById(R.id.pdfView);

        getVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }else{
                    selectVideo();
                }
            }
        });

        getPdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) getContext(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }else{
                    selectPdf();
                }
            }
        });

        return builder.create();
    }

    private void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 200);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "must implement ExampleDialogListener");
        }
    }

    //interface to send data from dialog box to activity
    public interface ExampleDialogListener{
        //add video uri as well as pdf
        void additem(String courseTitle,String vUri, String pdfUri);
    }

    private void selectVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("video/*");
        startActivityForResult(Intent.createChooser(intent,"SelectVideo"),100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            selectVideo();
        }else{
            Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 100 && data!=null){
            videoUri = data.getData();
            videoView.setVisibility(View.VISIBLE);
            videoView.setVideoURI(videoUri);
            videoView.start();

            final StorageReference reference1 = storage.getReference().child("posts")
                    .child("courseContent").child("videos/")
                    .child(new Date().getTime()+"");
            reference1.putFile(videoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           Vuri = uri.toString();
                           Toast.makeText(getContext(), "Video uploaded", Toast.LENGTH_SHORT).show();
                       }
                   }) ;
                }
            });

        }

        if(requestCode == 200 && data!=null){
            pdfUri = data.getData();
            pdfView.setVisibility(View.VISIBLE);
            pdfView.fromUri(pdfUri).load();

            final StorageReference reference2 = storage.getReference().child("posts")
                    .child("courseContent").child("pdfs/")
                    .child(new Date().getTime()+"");
            reference2.putFile(pdfUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            pdfuri = uri.toString();
                            Toast.makeText(getContext(), "pdf uploaded", Toast.LENGTH_SHORT).show();
                        }
                    }) ;
                }
            });
        }
    }
}