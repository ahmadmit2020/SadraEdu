package com.am.android.sadraedu.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.am.android.sadraedu.Adapter.TeacherAdapter;
import com.am.android.sadraedu.Model.Teacher;
import com.am.android.sadraedu.Retrofit.Api;
import com.am.android.sadraedu.Retrofit.ApiInterface;
import com.am.android.sadraedu.Tools.MySnackBar;
import com.am.android.sadraedu.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<List<Teacher>> {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ApiInterface anInterface = Api.api().create(ApiInterface.class);

        Call<List<Teacher>> call = anInterface.getTeachers();

        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                call.clone().enqueue(MainActivity.this);
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        binding.refreshLayout.setRefreshing(true);
        call.enqueue(MainActivity.this);

    }

    @Override
    public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
        binding.refreshLayout.setRefreshing(false);

        if (response.code() == 200) {

            List<Teacher> teachers = new ArrayList<>();
            for (int i = 0; i < response.body().size(); i++) {
                if (response.body().get(i).getName() != null){
                    teachers.add(response.body().get(i));
                }
            }
            binding.recyclerView.setAdapter(new TeacherAdapter(teachers, MainActivity.this));
        } else {
            MySnackBar.MySnackBarMarginButton(binding.getRoot(), MainActivity.this, 2, "مشکلی در ارتباط با سرور رخ داده است", "تلاش دوباره", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.refreshLayout.setRefreshing(true);

                    call.clone().enqueue(MainActivity.this);

                }
            });
        }
    }

    @Override
    public void onFailure(Call<List<Teacher>> call, Throwable t) {
        binding.refreshLayout.setRefreshing(false);

        MySnackBar.MySnackBarMarginButton(binding.getRoot(), MainActivity.this, 2, "مشکلی در ارتباط با سرور رخ داده است", "تلاش دوباره", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.refreshLayout.setRefreshing(true);

                call.clone().enqueue(MainActivity.this);

            }
        });
    }
}