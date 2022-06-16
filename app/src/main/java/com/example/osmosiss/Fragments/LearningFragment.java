package com.example.osmosiss.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.osmosiss.Adapters.CategoryAdapter;
import com.example.osmosiss.Adapters.CourseAdapter;
import com.example.osmosiss.Adapters.PopularCourseAdapter;
import com.example.osmosiss.Models.CategoryItems;
import com.example.osmosiss.Models.CourseItems;
import com.example.osmosiss.R;
import com.example.osmosiss.databinding.FragmentLearningBinding;

import java.util.ArrayList;
import java.util.List;


public class LearningFragment extends Fragment {

    private FragmentLearningBinding binding;

    private PopularCourseAdapter popularCourseAdapter;
    private PopularCourseAdapter courseAdapter;
    private CategoryAdapter categoryAdapter;

    private List<CourseItems> courseArrayList;
    private List<CourseItems> popularCourseArrayList;
    private List<CategoryItems> categoryArrayList;

    public LearningFragment(){
        //Empty Constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLearningBinding.inflate(getLayoutInflater(), container, false);

        courseArrayList = new ArrayList<>();
        popularCourseArrayList = new ArrayList<>();
        categoryArrayList = new ArrayList<>();

        binding.recyclerviewYourCourse.setLayoutManager(new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false));

        binding.recyclerviewPopular.setLayoutManager(new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false));

        binding.recyclerviewCategory.setLayoutManager(new LinearLayoutManager(getContext()
                ,LinearLayoutManager.HORIZONTAL,false));

        binding.recyclerviewYourCourse.setHasFixedSize(true);
        binding.recyclerviewPopular.setHasFixedSize(true);
        binding.recyclerviewCategory.setHasFixedSize(true);

        courseAdapter = new PopularCourseAdapter(getContext(),courseArrayList);
        binding.recyclerviewYourCourse.setAdapter(courseAdapter);
        getYourCourse();
        courseAdapter.notifyDataSetChanged();

        popularCourseAdapter = new PopularCourseAdapter(getContext(),popularCourseArrayList);
        binding.recyclerviewPopular.setAdapter(popularCourseAdapter);
        getPopularCourses();
        popularCourseAdapter.notifyDataSetChanged();

        categoryAdapter= new CategoryAdapter(getContext(),categoryArrayList);
        binding.recyclerviewCategory.setAdapter(categoryAdapter);
        getCategory();
        categoryAdapter.notifyDataSetChanged();

        return binding.getRoot();
    }

    private void getYourCourse() {
        courseArrayList.add(new CourseItems("Web Development","https://cdn1.vectorstock.com/i/1000x1000/95/70/web-development-courses-concept-metaphor-vector-38339570.jpg"));
        courseArrayList.add(new CourseItems("Machine Learning","https://i.pinimg.com/736x/75/15/47/75154779c924392ec7ff3ec36a3759ea.jpg"));
        courseArrayList.add(new CourseItems("Android","https://i.pinimg.com/736x/f6/a0/ee/f6a0eebb32ca7d28e8eb146d93c9d9bc.jpg"));
        courseArrayList.add(new CourseItems("Digital Marketing","https://png.pngtree.com/png-vector/20200312/ourmid/pngtree-modern-flat-design-concept-of-digital-marketing-with-giant-megaphone-and-png-image_2157858.jpg"));
        courseArrayList.add(new CourseItems("UI/UX","https://img.freepik.com/free-vector/gradient-ui-ux-background_23-2149051555.jpg?w=2000"));
    }

    private void getPopularCourses(){
        popularCourseArrayList.add(new CourseItems("Android","https://i.pinimg.com/736x/f6/a0/ee/f6a0eebb32ca7d28e8eb146d93c9d9bc.jpg"));
        popularCourseArrayList.add(new CourseItems("Digital Marketing","https://png.pngtree.com/png-vector/20200312/ourmid/pngtree-modern-flat-design-concept-of-digital-marketing-with-giant-megaphone-and-png-image_2157858.jpg"));
        popularCourseArrayList.add(new CourseItems("UI/UX","https://img.freepik.com/free-vector/gradient-ui-ux-background_23-2149051555.jpg?w=2000"));
        popularCourseArrayList.add(new CourseItems("Web Development","https://cdn1.vectorstock.com/i/1000x1000/95/70/web-development-courses-concept-metaphor-vector-38339570.jpg"));
        popularCourseArrayList.add(new CourseItems("Machine Learning","https://i.pinimg.com/736x/75/15/47/75154779c924392ec7ff3ec36a3759ea.jpg"));
        popularCourseArrayList.add(new CourseItems("Android","https://i.pinimg.com/736x/f6/a0/ee/f6a0eebb32ca7d28e8eb146d93c9d9bc.jpg"));
        popularCourseArrayList.add(new CourseItems("Digital Marketing","https://png.pngtree.com/png-vector/20200312/ourmid/pngtree-modern-flat-design-concept-of-digital-marketing-with-giant-megaphone-and-png-image_2157858.jpg"));
        popularCourseArrayList.add(new CourseItems("UI/UX","https://img.freepik.com/free-vector/gradient-ui-ux-background_23-2149051555.jpg?w=2000"));
    }

    private void getCategory(){

        categoryArrayList.add(new CategoryItems("Finance",R.drawable.accounting));
        categoryArrayList.add(new CategoryItems("Development",R.drawable.development));
        categoryArrayList.add(new CategoryItems("Business",R.drawable.businessandfinance));
        categoryArrayList.add(new CategoryItems("IT and Software",R.drawable.it_and_software));
        categoryArrayList.add(new CategoryItems("Office Productivity",R.drawable.officeproductivity));
        categoryArrayList.add(new CategoryItems("Marketing",R.drawable.marketing));
        categoryArrayList.add(new CategoryItems("Music",R.drawable.musicnote));
        categoryArrayList.add(new CategoryItems("Photography",R.drawable.photography));
        categoryArrayList.add(new CategoryItems("Design",R.drawable.design));
        categoryArrayList.add(new CategoryItems("Health",R.drawable.health));

    }
}