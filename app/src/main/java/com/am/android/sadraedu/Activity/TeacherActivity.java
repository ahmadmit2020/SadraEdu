package com.am.android.sadraedu.Activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.am.android.sadraedu.Adapter.FishAdapter;
import com.am.android.sadraedu.Adapter.OnDeleteFish;
import com.am.android.sadraedu.Model.CreateFishResponse;
import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.Model.Teacher;
import com.am.android.sadraedu.R;
import com.am.android.sadraedu.Retrofit.Api;
import com.am.android.sadraedu.Retrofit.ApiInterface;
import com.am.android.sadraedu.Tools.MySnackBar;
import com.am.android.sadraedu.databinding.ActivityTeacherBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivity extends AppCompatActivity implements Callback<List<Fish>>, OnDeleteFish {

    ActivityTeacherBinding binding;
    Teacher teacher;
    LayoutAnimationController layout_animation;
    ApiInterface anInterface;
    Call<List<Fish>> get_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        teacher = (Teacher) getIntent().getSerializableExtra("teacher");
        layout_animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation);


        binding.teacherNameText.setText(teacher.getName());
        binding.teacherBirthPlace.setText("محل تولد : " + teacher.getBirth_place());
        binding.teacherFatherName.setText("نام پدر : " + teacher.getFather_name());
        binding.teacherDegreeText.setText("مدرک  : " + teacher.getFather_name());
        binding.teacherAddressText.setText("آدرس  : " + teacher.getAddress());
        binding.teacherMajorText.setText("رشته  : " + teacher.getMajor());
        binding.teacherNationalCode.setText("کد ملی  : " + teacher.getNational_code());
        binding.teacherMobileNumber.setText("شماره موبایل  : " + teacher.getSpouse_no());

            anInterface = Api.api().create(ApiInterface.class);

        get_call = anInterface.getTeacherFishes(teacher.getId());

        binding.progressBar.setVisibility(View.VISIBLE);
        get_call.enqueue(TeacherActivity.this);

        binding.addFishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowAddFishBottomSheet(get_call);
            }
        });

    }

    private void ShowAddFishBottomSheet(Call<List<Fish>> get_fishes_call) {
        View view = LayoutInflater.from(TeacherActivity.this).inflate(R.layout.bottom_sheet_add_fish, binding.getRoot(), false);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this, R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(view);


        Button button_save = view.findViewById(R.id.add_fish_button_bs);
        EditText editText_year, editText_month, editText_days, editText_maliat, editText_per_day;
        editText_year = view.findViewById(R.id.fish_year_edit_text);
        editText_month = view.findViewById(R.id.fish_month_edit_text);
        editText_days = view.findViewById(R.id.fish_days_edit_text);
        editText_per_day = view.findViewById(R.id.fish_per_day_edit_text);
        editText_maliat = view.findViewById(R.id.fish_maliat_edit_text);


        ProgressBar progressBar = view.findViewById(R.id.progress_bar);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText_year.getText().toString().length() == 0 ||
                        editText_month.getText().toString().length() == 0 ||
                        editText_days.getText().toString().length() == 0 ||
                        editText_per_day.getText().toString().length() == 0 ||
                        editText_maliat.getText().toString().length() == 0
                ) {
                    Toast.makeText(TeacherActivity.this, "لطفا تمام فیلد ها را پر کنید !", Toast.LENGTH_SHORT).show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    Call<CreateFishResponse> addFishCall = anInterface.addFish(teacher.getId(),
                            Integer.parseInt(editText_month.getText().toString()),
                            Integer.parseInt(editText_days.getText().toString()),
                            Integer.parseInt(editText_maliat.getText().toString()),
                            Integer.parseInt(editText_per_day.getText().toString()),
                            Integer.parseInt(editText_year.getText().toString())
                    );

                    addFishCall.enqueue(new Callback<CreateFishResponse>() {
                        @Override
                        public void onResponse(Call<CreateFishResponse> call, Response<CreateFishResponse> response) {
                            progressBar.setVisibility(View.INVISIBLE);
                            if (response.code() == 200) {
                                bottomSheetDialog.dismiss();
                                MySnackBar.MySnackBarMargin(binding.getRoot(), TeacherActivity.this, 1, "فیش مورد نظر با موفقیت اضافه شد .");
                                editText_month.setText("");
                                editText_days.setText("");
                                editText_maliat.setText("");
                                editText_per_day.setText("");
                                editText_year.setText("");
                                binding.progressBar.setVisibility(View.VISIBLE);
                                get_fishes_call.clone().enqueue(TeacherActivity.this);
                            } else {

                                Toast.makeText(TeacherActivity.this, "مشکلی در ارتباط با سرور رخ داده است  !", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<CreateFishResponse> call, Throwable t) {
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(TeacherActivity.this, "مشکلی در ارتباط با سرور رخ داده است  !", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });


        setupFullHeight(bottomSheetDialog);

        bottomSheetDialog.show();

    }

    private void ConfirmDeleteAlertDialog(int fish_id) {
        View view = LayoutInflater.from(this).inflate(R.layout.alert_dialog_confirm_delete, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        TextView textView_cancel = view.findViewById(R.id.login_fragment_login_button);
        TextView textView_delete = view.findViewById(R.id.login_fragment_cancel_button);
        textView_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();

            }
        });
        textView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<String> delete_call = anInterface.deleteFish(fish_id);
                delete_call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        MySnackBar.MySnackBarMargin(binding.getRoot(), TeacherActivity.this, 1, "فیش مورد نظر با موفقیت حذف شد .");
                        binding.progressBar.setVisibility(View.VISIBLE);
                        get_call.clone().enqueue(TeacherActivity.this);

                        alertDialog.cancel();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        MySnackBar.MySnackBarMargin(binding.getRoot(), TeacherActivity.this, 2, "مشکلی در ارتباط با سرور رخ داده است .");
                        alertDialog.cancel();

                    }
                });
            }
        });
        alertDialog.show();
    }



    @Override
    public void onResponse(Call<List<Fish>> call, Response<List<Fish>> response) {
        binding.progressBar.setVisibility(View.GONE);
        if (response.code() == 200) {
            binding.recyclerView.setLayoutAnimation(layout_animation);

            binding.recyclerView.setAdapter(new FishAdapter(response.body(), TeacherActivity.this, TeacherActivity.this));
        } else {
            ShowErrorSnackBar(call);
        }
    }

    private void ShowErrorSnackBar(Call<List<Fish>> call) {
        MySnackBar.MySnackBarMarginButton(binding.getRoot(), TeacherActivity.this, 2, "مشکلی در ارتباط با سرور رخ داده است", "تلاش دوباره", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.progressBar.setVisibility(View.VISIBLE);

                call.clone().enqueue(TeacherActivity.this);

            }
        });
    }

    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        (TeacherActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    @Override
    public void onFailure(Call<List<Fish>> call, Throwable t) {
        binding.progressBar.setVisibility(View.GONE);
        ShowErrorSnackBar(call);

    }

    @Override
    public void OnFishDeleteClicked(int fish_id) {
        ConfirmDeleteAlertDialog(fish_id);
    }
}