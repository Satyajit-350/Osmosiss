package com.example.osmosiss.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.osmosiss.Adapters.EducationAdapter;
import com.example.osmosiss.Adapters.ExperienceAdapter;
import com.example.osmosiss.Adapters.ProjectAdapter;
import com.example.osmosiss.Adapters.SkillsAdapter;
import com.example.osmosiss.Course.CreateCourse.NewSectionActivity;
import com.example.osmosiss.InstructorActivity;
import com.example.osmosiss.MainActivity;
import com.example.osmosiss.Models.EducationModel;
import com.example.osmosiss.Models.ExperienceModel;
import com.example.osmosiss.Models.ProjectModel;
import com.example.osmosiss.Models.Users;
import com.example.osmosiss.R;
import com.example.osmosiss.RecruiterActivity;
import com.example.osmosiss.databinding.ActivityEditProfileBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements NewProjectSection.ProjectDialogListener,
NewSkillsSection.SkillsDialogListener,NewExperienceSection.ExperienceDialogListener,NewEducationSection.EducationDialogListener{

    private ActivityEditProfileBinding binding;

    private SkillsAdapter skillsAdapter;
    private List<String> skillsArrayList;

    private EducationAdapter educationAdapter;
    private List<EducationModel> educationArrayList;

    private ExperienceAdapter experienceAdapter;
    private List<ExperienceModel> experienceArrayList;

    private ProjectAdapter projectAdapter;
    private List<ProjectModel> projectArrayList;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private ProgressDialog dialog;

    private List<String> dummySkillList;
    private List<EducationModel> dummyEducationList;
    private List<ExperienceModel> dummyExperienceList;
    private List<ProjectModel> dummyProjectList;

    private int userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        skillsArrayList = new ArrayList<>();
        educationArrayList = new ArrayList<>();
        experienceArrayList = new ArrayList<>();
        projectArrayList = new ArrayList<>();

        dummySkillList = new ArrayList<>();
        dummyEducationList = new ArrayList<>();
        dummyExperienceList = new ArrayList<>();
        dummyProjectList = new ArrayList<>();

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Post Uploading");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        binding.skillsRV.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.skillsRV.setHasFixedSize(true);
        skillsAdapter = new SkillsAdapter(this,skillsArrayList);
        binding.skillsRV.setAdapter(skillsAdapter);

        //todo getSkills()

        binding.educationRV.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.educationRV.setHasFixedSize(true);
        educationAdapter = new EducationAdapter(this,educationArrayList);
        binding.educationRV.setAdapter(educationAdapter);

        //todo getEducation()

        binding.experienceRV.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        binding.experienceRV.setHasFixedSize(true);
        experienceAdapter = new ExperienceAdapter(this,experienceArrayList);
        binding.experienceRV.setAdapter(experienceAdapter);

        //todo getExperiences()

        binding.projectRV.setLayoutManager(new LinearLayoutManager(this){
            @Override
            public boolean canScrollVertically() {
                return false;
            }

        });
        binding.projectRV.setHasFixedSize(true);
        projectAdapter = new ProjectAdapter(this,projectArrayList);
        binding.projectRV.setAdapter(projectAdapter);

        //todo getProjects

        binding.AddProjectTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProjectDialog();
            }
        });

        binding.AddSkillsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSkillsDialog();
            }
        });

        binding.AddExperienceTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExperienceDialog();
            }
        });

        binding.AddEducationTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEducationDialog();
            }
        });

        database.getReference().child("Users").child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                Picasso.get().load(users.getProfile_pic()).placeholder(R.drawable.user_img).into(binding.circleImageView2);
                binding.userProfileName.setText(users.getUsername());
                binding.profileProfession.setText(users.getProfession());
                binding.profileAbout.setText(users.getAbout());
                binding.profileMail.setText(users.getEmail());
                binding.profilePhone.setText(users.getPhone());
                binding.profileLocation.setText(users.getCountry());

                userType = users.getIsAdmin();

                dummySkillList = users.getSkills();
                if(dummySkillList!=null){
                    for(int i=0;i<dummySkillList.size();i++){
                        skillsArrayList.add(dummySkillList.get(i));
                    }
                    skillsAdapter.notifyDataSetChanged();
                }
                dummyEducationList = users.getEducation();
                if(dummyEducationList!=null){
                    for(int i=0;i<dummyEducationList.size();i++){
                        educationArrayList.add(dummyEducationList.get(i));
                    }
                    educationAdapter.notifyDataSetChanged();
                }
                dummyExperienceList = users.getExperience();
                if(dummyExperienceList!=null){
                    for(int i=0;i<dummyExperienceList.size();i++){
                        experienceArrayList.add(dummyExperienceList.get(i));
                    }
                    experienceAdapter.notifyDataSetChanged();
                }
                dummyProjectList = users.getProject();
                if(dummyProjectList!=null){
                    for(int i=0;i<dummyProjectList.size();i++){
                        projectArrayList.add(dummyProjectList.get(i));
                    }
                    projectAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebase part
                dialog.show();
                String user_name = binding.userProfileName.getText().toString();
                String profession = binding.profileProfession.getText().toString();
                String about = binding.profileAbout.getText().toString();
                String mail = binding.profileMail.getText().toString().trim();
                String phone = binding.profilePhone.getText().toString().trim();
                String country = binding.profileLocation.getText().toString().trim();

                HashMap<String,Object> obj = new HashMap<>();
                obj.put("username",user_name);
                obj.put("profession",profession);
                obj.put("about",about);
                obj.put("email",mail);
                obj.put("phone",phone);
                obj.put("country",country);

                database.getReference().child("Users").child(mAuth.getUid())
                        .child("skills")
                        .setValue(skillsArrayList);
                database.getReference().child("Users").child(mAuth.getUid())
                        .child("education")
                        .setValue(educationArrayList);
                database.getReference().child("Users").child(mAuth.getUid())
                        .child("experience")
                        .setValue(experienceArrayList);
                database.getReference().child("Users").child(mAuth.getUid())
                        .child("project")
                        .setValue(projectArrayList);

                database.getReference().child("Users").child(mAuth.getUid())
                        .updateChildren(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.dismiss();
                                Toast.makeText(EditProfileActivity.this, "Profile updated", Toast.LENGTH_SHORT).show();
                                if(userType==0){
                                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else if (userType==1){
                                    Intent intent = new Intent(EditProfileActivity.this, InstructorActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else if(userType==2){
                                    Intent intent = new Intent(EditProfileActivity.this, RecruiterActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });


            }
        });

        binding.addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    private void openEducationDialog() {
        NewEducationSection dialog = new NewEducationSection();
        dialog.show(getSupportFragmentManager(),"add new education");
    }

    private void openExperienceDialog() {
        NewExperienceSection dialog = new NewExperienceSection();
        dialog.show(getSupportFragmentManager(),"add new experience");
    }

    private void openSkillsDialog() {
        NewSkillsSection dialog = new NewSkillsSection();
        dialog.show(getSupportFragmentManager(),"add new skills");
    }

    private void openProjectDialog() {
        NewProjectSection dialog = new NewProjectSection();
        dialog.show(getSupportFragmentManager(),"add new projects");
    }
    @Override
    public void addProject(String projectTitle, String githubLink, String details, String startDt, String endDt) {
        projectArrayList.add(new ProjectModel(projectTitle,githubLink,startDt,endDt,details));
        projectAdapter.notifyDataSetChanged();
    }

    @Override
    public void addSkills(String skillTitle) {
        skillsArrayList.add(skillTitle);
        skillsAdapter.notifyDataSetChanged();
    }

    @Override
    public void addExperience(String title, String companyName, String startDt, String endDt, String desc) {
        experienceArrayList.add(new ExperienceModel(title,companyName,startDt,endDt,desc));
        experienceAdapter.notifyDataSetChanged();
    }

    @Override
    public void addEducation(String instituteName, String degree, String startDt, String endDt, String grade) {
        educationArrayList.add(new EducationModel(instituteName,degree,startDt,endDt,grade));
        educationAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData()!=null){
            Uri file = data.getData();
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.circleImageView2.setImageURI(file);

            final StorageReference reference = storage.getReference().child("Profile_picture")
                    .child(mAuth.getUid());
            reference.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(mAuth.getUid())
                                    .child("profile_pic").setValue(uri.toString());
                            binding.progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(EditProfileActivity.this, "Profile picture updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}