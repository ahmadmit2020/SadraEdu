package com.am.android.sadraedu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.am.android.sadraedu.Activity.TeacherActivity;
import com.am.android.sadraedu.Model.Teacher;
import com.am.android.sadraedu.R;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    private List<Teacher> teachers;
    private Context context;


    public TeacherAdapter(List<Teacher> teachers, Context context) {
        this.teachers = teachers;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teacher_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teachers.get(position);
        String name = teacher.getName() == null ? "بدون نام" : teacher.getName();
        holder.textView_name.setText(name);

        String degree = teacher.getDegree() == null ? "__" : teacher.getDegree();
        holder.textView_education_degree.setText(degree);

        String major = teacher.getMajor() == null ? "__" : teacher.getMajor();
        holder.textView_education_major.setText(major);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context , TeacherActivity.class).putExtra("teacher" , teacher));
            }
        });


    }


    @Override
    public int getItemCount() {
        return teachers.size();
    }

    public class TeacherViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_name, textView_education_degree, textView_education_major;
        public CardView cardView;
        public TeacherViewHolder(@NonNull View view) {
            super(view);
            textView_name = view.findViewById(R.id.teacher_name_text);
            textView_education_degree = view.findViewById(R.id.teacher_degree_text);
            textView_education_major = view.findViewById(R.id.teacher_father_name);
            cardView = view.findViewById(R.id.teacher_card);

        }
    }
}
