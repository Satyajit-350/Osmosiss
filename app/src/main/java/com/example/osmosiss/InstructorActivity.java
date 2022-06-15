package com.example.osmosiss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.example.osmosiss.Fragments.FeaturedFragment;
import com.example.osmosiss.Fragments.InstructorAccountFragment;
import com.example.osmosiss.Fragments.InstructorHomeFragment;
import com.example.osmosiss.Fragments.InstructorPostFragment;
import com.example.osmosiss.Fragments.RecruiterAccountFragment;
import com.example.osmosiss.Fragments.RecruiterHomeFragment;
import com.example.osmosiss.Fragments.RecruiterPostFragment;
import com.example.osmosiss.databinding.ActivityInstructorBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class InstructorActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener{

    private ActivityInstructorBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstructorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        loadFragment(new FeaturedFragment());
        binding.instructorBottomNav.setOnItemSelectedListener(this);
        binding.instructorBottomNav.setItemSelected(R.id.instructor_featured,true);

    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.instructor_frame_layouts_container,fragment)
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
                fragment=new InstructorHomeFragment();
                break;
            case R.id.instructor_my_post:
                fragment=new InstructorPostFragment();
                break;
            case R.id.instructor_account:
                fragment=new InstructorAccountFragment();
                break;
        }
        loadFragment(fragment);
    }
}