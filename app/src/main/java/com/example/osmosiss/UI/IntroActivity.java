package com.example.osmosiss.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.MainActivity;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.example.osmosiss.RecruiterActivity;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.SignInAndSignUp.SignUpActivity;
import com.example.osmosiss.databinding.ActivityIntroBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        binding.Signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if user is already signIn
        if(mAuth.getCurrentUser()!=null){
            checkUserType();
        }
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
                        Intent intent1 = new Intent(IntroActivity.this, MainActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case 1:
                        Intent intent2 = new Intent(IntroActivity.this, InstructorActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case 2:
                        Intent intent3 = new Intent(IntroActivity.this, RecruiterActivity.class);
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
}