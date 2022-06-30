package com.example.osmosiss.Course.CreateCourse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.osmosiss.R;
import com.example.osmosiss.databinding.ActivityAddCourseBinding;
import com.google.gson.Gson;

import java.util.HashMap;

public class AddCourseActivity extends AppCompatActivity {

    private ActivityAddCourseBinding binding;
    private String[] categories = {"Development","Marketing","Business","Music","Photography"};
    private String[] levels = {"Easy","Medium","Difficult"};
    private String[] language = {"English","Hindi"};
    private String[] subCategory = {"Web Development","Digital Marketing","Android Development","Machine learning","AI"};
    private ArrayAdapter<String> adapterCategory,adapterSubCategory,adapterLanguage,adapterLevel;
    private Uri uri;
    private HashMap<String,String> dataHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataHashMap = new HashMap<>();

        adapterCategory = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,categories);
        adapterSubCategory = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,subCategory);
        adapterLanguage = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,language);
        adapterLevel = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,levels);

        binding.categoryET.setAdapter(adapterCategory);
        binding.categoryET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        binding.imgSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.categoryET.showDropDown();
            }
        });

        binding.SubCategoryET.setAdapter(adapterSubCategory);
        binding.SubCategoryET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        binding.imgSearchSubCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.SubCategoryET.showDropDown();
            }
        });

        binding.LanguageET.setAdapter(adapterLanguage);
        binding.LanguageET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        binding.imgSearchLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.LanguageET.showDropDown();
            }
        });

        binding.LevelET.setAdapter(adapterLevel);
        binding.LevelET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
            }
        });
        binding.imgSearchLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.LevelET.showDropDown();
            }
        });

        binding.addImageIVCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });


        binding.NextTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //putAllDataInSharedPref();
                Intent intent = new Intent(AddCourseActivity.this,AddCourseContentActivity.class);
                intent.putExtra("Title",binding.courseTitleTV.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void putAllDataInSharedPref() {
        dataHashMap.clear();
        dataHashMap.put("CourseTitle",binding.courseTitleTV.getText().toString());
        dataHashMap.put("CourseSubTitle",binding.courseSubtitleTV.getText().toString());
        dataHashMap.put("CourseDescription",binding.courseDescriptionTV.getText().toString());
        dataHashMap.put("CourseCategory",binding.categoryET.getText().toString());
        dataHashMap.put("CourseSubCategory",binding.SubCategoryET.getText().toString());
        dataHashMap.put("CourseLanguage",binding.LanguageET.getText().toString());
        dataHashMap.put("CourseLevel",binding.LevelET.getText().toString());
        dataHashMap.put("CoursePic",uri.toString());

        Gson gson = new Gson();
        String dataHashMapString = gson.toJson(dataHashMap);

        SharedPreferences sharedPreferences = getSharedPreferences("NewCourse",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dataNewCourse", dataHashMapString).apply();

        /**
         * TODO
         *
         *     //get from shared prefs
         *     String storedHashMapString = prefs.getString("hashString", "oopsDintWork");
         *     java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
         *     HashMap<String, String> testHashMap2 = gson.fromJson(storedHashMapString, type);
         *
         *     //use values
         *     String toastString = testHashMap2.get("key1") + " | " + testHashMap2.get("key2");
         *     Toast.makeText(this, toastString, Toast.LENGTH_LONG).show();
         */
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            uri = data.getData();
            binding.courseIV.setImageURI(uri);
        }
    }
}