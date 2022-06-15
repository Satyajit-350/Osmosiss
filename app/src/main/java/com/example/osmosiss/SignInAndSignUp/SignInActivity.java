package com.example.osmosiss.SignInAndSignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.MainActivity;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.RecruiterActivity;
import com.example.osmosiss.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog dialog;
    private boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        dialog = new ProgressDialog(SignInActivity.this);
        dialog.setTitle("SignIn");
        dialog.setMessage("SignIn to your Account");

        binding.signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(binding.editTextEmail);
                checkField(binding.editTextPassword);
                String email = binding.editTextEmail.getText().toString();
                String password = binding.editTextPassword.getText().toString();
                dialog.show();
                if(valid){
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if(task.isSuccessful()){
                                checkUserType();
                            }else{
                                Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    dialog.dismiss();
                }
            }
        });

        binding.newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void checkUserType() {
        DatabaseReference databaseReference = database.getReference().child("Users").child(mAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                int userType = users.getIsAdmin();
                switch(userType){
                    case 0:
                        Intent intent1 = new Intent(SignInActivity.this,MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 1:
                        Intent intent2 = new Intent(SignInActivity.this, InstructorActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case 2:
                        Intent intent3 = new Intent(SignInActivity.this, RecruiterActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        //if user is already signIn
//        if(mAuth.getCurrentUser()!=null){
//            checkUserType();
//        }
//    }

}