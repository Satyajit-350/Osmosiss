package com.example.osmosiss.SignInAndSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.MainActivity;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.example.osmosiss.RecruiterActivity;
import com.example.osmosiss.databinding.ActivitySignInBinding;
import com.example.osmosiss.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog dialog;
    private boolean valid = true;

    int instructor = 1;
    int user = 0;
    int recruiter = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(SignUpActivity.this);
        dialog.setTitle("Please Wait");
        dialog.setMessage("Creating your Account");

        binding.isStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    binding.isAdmin.setChecked(false);
                    binding.isTeacher.setChecked(false);
                }
            }
        });

        binding.isAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    binding.isStudent.setChecked(false);
                    binding.isTeacher.setChecked(false);
                }
            }
        });

        binding.isTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    binding.isStudent.setChecked(false);
                    binding.isAdmin.setChecked(false);
                }
            }
        });

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                checkField(binding.registerEditTextEmail);
                checkField(binding.registerEditTextName);
                checkField(binding.registerEditTextPassword);
                dialog.show();
                String name = binding.registerEditTextName.getText().toString();
                String email = binding.registerEditTextEmail.getText().toString();
                String password = binding.registerEditTextPassword.getText().toString();

                if(!valid&&!(binding.isStudent.isChecked()||binding.isTeacher.isChecked()||binding.isAdmin.isChecked())){
                    dialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Please select admin or user or teacher", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    dialog.dismiss();
                    mAuth.createUserWithEmailAndPassword(binding.registerEditTextEmail.getText().toString()
                            ,binding.registerEditTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Users users;
                                if(binding.isAdmin.isChecked()){
                                    dialog.show();
                                    users = new Users(name,password,email,instructor);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(users);

                                    Intent intent = new Intent(SignUpActivity.this, InstructorActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }else if(binding.isTeacher.isChecked()){
                                    dialog.show();
                                    users = new Users(name,password,email,recruiter);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(users);

                                    Intent intent = new Intent(SignUpActivity.this, RecruiterActivity.class);
                                    startActivity(intent);
                                    finish();

                                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                                else{
                                    dialog.show();;
                                    users = new Users(name,password,email,user);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(users);

                                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }else{
                                dialog.dismiss();
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        binding.alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public boolean checkField(TextInputEditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }
}