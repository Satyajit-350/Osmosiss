package com.example.osmosiss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.osmosiss.Adapters.RecyclerViewAdapter;
import com.example.osmosiss.Fragments.AccountFragment;
import com.example.osmosiss.Fragments.FeaturedFragment;
import com.example.osmosiss.Fragments.LearningFragment;
import com.example.osmosiss.Fragments.WishListFragment;
import com.example.osmosiss.Models.data;
import com.example.osmosiss.SignInAndSignUp.SignInActivity;
import com.example.osmosiss.databinding.ActivityMainBinding;
import com.google.android.gms.common.internal.Objects;
import com.google.firebase.auth.FirebaseAuth;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {

    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        loadFragment(new FeaturedFragment());
        binding.bottomNav.setOnItemSelectedListener(this);
        binding.bottomNav.setItemSelected(R.id.featured,true);

    }

    private void loadFragment(Fragment fragment) {
        if(fragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layouts_container,fragment)
                    .commit();
        }
        else{
            Toast.makeText(this, "Fragment Loading error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item1, menu);
        menuInflater.inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logOut:
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(this, "SignOut Successful", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(int i) {
        Fragment fragment = null;
        switch(i){
            case R.id.featured:
                fragment=new FeaturedFragment();
                break;
            case R.id.my_learning:
                fragment=new LearningFragment();
                break;
            case R.id.favorite:
                fragment=new WishListFragment();
                break;
            case R.id.account:
                fragment=new AccountFragment();
                break;
        }
        loadFragment(fragment);
    }
}