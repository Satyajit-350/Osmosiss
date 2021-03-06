package com.example.osmosiss.SignInAndSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
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

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog dialog;
    private boolean valid = true;

    private ArrayList<Integer> userList = new ArrayList<>();
    final String[] userType = new String[]{"User","Instructor","Recruiter"};
    final int[] selectedUserType = {-1};

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

        binding.selectUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserDialog();
            }
        });

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                checkField(binding.registerEditTextEmail);
                checkField(binding.registerEditTextName);
                checkField(binding.registerEditTextPassword);
                String name = binding.registerEditTextName.getText().toString();
                String email = binding.registerEditTextEmail.getText().toString();
                String password = binding.registerEditTextPassword.getText().toString();

                String userTitle = binding.selectedUser.getText().toString();

                if(!valid||userTitle==""){
                    dialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Please select User or Instructor or Recruiter", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    dialog.dismiss();
                    mAuth.createUserWithEmailAndPassword(binding.registerEditTextEmail.getText().toString()
                            ,binding.registerEditTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Users users;
                                if(binding.selectedUser.getText().equals("Instructor")){
                                    dialog.show();
                                    users = new Users(name,password,email,instructor);
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(users);

                                    Intent intent = new Intent(SignUpActivity.this, InstructorActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(SignUpActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();

                                }else if(binding.selectedUser.getText().equals("Recruiter")){
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
                                else if(binding.selectedUser.getText().equals("User")){
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

    private void showUserDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select User");
        builder.setCancelable(false);
        builder.setSingleChoiceItems(userType, selectedUserType[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedUserType[0] = which;
                binding.selectedUser.setText(userType[which]);
                dialog.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public boolean checkField(EditText textField){
        if(textField.getText().toString().isEmpty()){
            textField.setError("Error");
            valid = false;
        }else {
            valid = true;
        }
        return valid;
    }
}