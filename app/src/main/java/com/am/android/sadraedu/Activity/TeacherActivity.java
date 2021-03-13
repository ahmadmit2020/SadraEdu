package com.am.android.sadraedu.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.am.android.sadraedu.Adapter.FishAdapter;
import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.Model.Teacher;
import com.am.android.sadraedu.Retrofit.Api;
import com.am.android.sadraedu.Retrofit.ApiInterface;
import com.am.android.sadraedu.databinding.ActivityTeacherBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivity extends AppCompatActivity implements Callback<List<Fish>> {

    ActivityTeacherBinding binding;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        teacher = (Teacher) getIntent().getSerializableExtra("teacher");


        binding.teacherNameText.setText(teacher.getName());
        binding.teacherBirthPlace.setText("محل تولد : " + teacher.getBirth_place());
        binding.teacherFatherName.setText("نام پدر : " + teacher.getFather_name());
        binding.teacherDegreeText.setText("مدرک  : " + teacher.getFather_name());
        binding.teacherAddressText.setText("آدرس  : " + teacher.getAddress());
        binding.teacherMajorText.setText("رشته  : " + teacher.getMajor());
        binding.teacherNationalCode.setText("کد ملی  : " + teacher.getNational_code());
        binding.teacherMobileNumber.setText("شماره موبایل  : " + teacher.getSpouse_no());

        ApiInterface anInterface = Api.api().create(ApiInterface.class);

        Call<List<Fish>> call = anInterface.getTeacherFishes(teacher.getId());

        binding.progressBar.setVisibility(View.VISIBLE);
        call.enqueue(TeacherActivity.this);

    }

    @Override
    public void onResponse(Call<List<Fish>> call, Response<List<Fish>> response) {
        binding.progressBar.setVisibility(View.GONE);
        if (response.code()==200){
            binding.recyclerView.setAdapter(new FishAdapter(response.body(),TeacherActivity.this));
        }else {

        }
    }

    @Override
    public void onFailure(Call<List<Fish>> call, Throwable t) {
        binding.progressBar.setVisibility(View.GONE);
    }
}