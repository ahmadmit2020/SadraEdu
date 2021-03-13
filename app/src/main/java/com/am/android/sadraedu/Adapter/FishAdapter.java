package com.am.android.sadraedu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.R;

import java.util.List;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.FishViewHolder> {
    private List<Fish> fishes;
    private Context context;

    public FishAdapter(List<Fish> fishes, Context context) {
        this.fishes = fishes;
        this.context = context;
    }


    @NonNull
    @Override
    public FishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FishViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fee_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FishViewHolder holder, int position) {
        Fish fish = fishes.get(position);

        String whole = String.format("%,d", Math.round(Float.parseFloat(fish.getWhole_salary())));
        String per_day = String.format("%,d", Integer.parseInt(fish.getSlalary_per_class()));

        holder.textView_year.setText("سال : " + fish.getYear());
        holder.textView_month.setText("ماه : " + fish.getMounth());
        holder.textView_per_day.setText("حقوق هر روز : " + per_day);
        holder.textView_maliat.setText("مالیات : " + fish.getM_percent() + "%");
        holder.textView_whole_salary.setText("مبلغ نهایی : " +whole);
        holder.textView_days.setText("تعداد روز ها : " + fish.getWorking_days());
    }

    @Override
    public int getItemCount() {
        return fishes.size();
    }

    public class FishViewHolder extends RecyclerView.ViewHolder {
        TextView textView_year, textView_month, textView_days, textView_maliat, textView_per_day, textView_whole_salary;

        public FishViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_year = itemView.findViewById(R.id.teacher_name_text);
            textView_month = itemView.findViewById(R.id.fish_month_text);
            textView_days = itemView.findViewById(R.id.teacher_degree_text);
            textView_maliat = itemView.findViewById(R.id.teacher_father_name);
            textView_per_day = itemView.findViewById(R.id.fish_per_day_text);
            textView_whole_salary = itemView.findViewById(R.id.fish_whole_salary);

        }
    }
}
