package com.example.osmosiss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.osmosiss.Fragments.AccountFragment;
import com.example.osmosiss.Fragments.FeaturedFragment;
import com.example.osmosiss.Fragments.LearningFragment;
import com.example.osmosiss.Fragments.RecruiterAccountFragment;
import com.example.osmosiss.Fragments.RecruiterHomeFragment;
import com.example.osmosiss.Fragments.RecruiterPostFragment;
import com.example.osmosiss.Fragments.WishListFragment;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.databinding.ActivityRecruiterBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class RecruiterActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener{

    private ActivityRecruiterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecruiterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        loadFragment(new FeaturedFragment());
        binding.recruiterBottomNav.setOnItemSelectedListener(this);
        binding.recruiterBottomNav.setItemSelected(R.id.instructor_featured,true);
    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recruiter_frame_layouts_container,fragment)
                    .commit();
        }
        else{
            Toast.makeText(this, "Fragment Loading error", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.instructor_bottom_menu, menu);
        return true;
    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment = null;
        switch(i){
            case R.id.instructor_featured:
                fragment=new RecruiterHomeFragment();
                break;
            case R.id.instructor_my_post:
                fragment=new RecruiterPostFragment();
                break;
            case R.id.instructor_account:
                fragment=new RecruiterAccountFragment();
                break;
        }
        loadFragment(fragment);
    }
}