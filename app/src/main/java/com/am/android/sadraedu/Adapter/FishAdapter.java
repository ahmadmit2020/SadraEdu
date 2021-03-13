package com.am.android.sadraedu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.R;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

import java.util.List;

public class FishAdapter extends RecyclerView.Adapter<FishAdapter.FishViewHolder> {
    private List<Fish> fishes;
    private Context context;
    private OnDeleteFish onDeleteFish;


    public FishAdapter(List<Fish> fishes, Context context, OnDeleteFish onDeleteFish) {
        this.fishes = fishes;
        this.context = context;
        this.onDeleteFish = onDeleteFish;
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
        holder.textView_per_day.setText("حقوق هر جلسه : " + per_day);
        holder.textView_maliat.setText("درصد کسری : " + fish.getM_percent() + "%");
        holder.textView_whole_salary.setText("مبلغ نهایی : " + whole);
        holder.textView_days.setText("تعداد جلسات: " + fish.getWorking_days());


        Animation animation = AnimationUtils.loadAnimation(context, R.anim.bounce);

        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imageView_delete.startAnimation(animation);
                onDeleteFish.OnFishDeleteClicked(fish.getId());
            }
        });

        holder.imageView_print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imageView_print.startAnimation(animation);

                holder.imageView_print.setVisibility(View.INVISIBLE);
                holder.imageView_delete.setVisibility(View.GONE);

                PdfGenerator.getBuilder()
                        .setContext(context)
                        .fromViewSource()
                        .fromView(holder.itemView)
                        .setPageSize(PdfGenerator.PageSize.A4)
                        .setFileName("File_PDF_Fish_" + fish.getId())
                        .setFolderName("PDF Fishes")
                        .openPDFafterGeneration(true)
                        .build(new PdfGeneratorListener() {
                            @Override
                            public void onFailure(FailureResponse failureResponse) {
                                super.onFailure(failureResponse);
                            }

                            @Override
                            public void showLog(String log) {
                                super.showLog(log);
                            }

                            @Override
                            public void onStartPDFGeneration() {
                                /*When PDF generation begins to start*/
                            }

                            @Override
                            public void onFinishPDFGeneration() {
                                /*When PDF generation is finished*/
                            }

                            @Override
                            public void onSuccess(SuccessResponse response) {
                                super.onSuccess(response);
                                Toast.makeText(context, "فایل با موفقیت در پوشه Pdf Fishes ذخیره شد . ", Toast.LENGTH_SHORT).show();

                                holder.imageView_print.setVisibility(View.VISIBLE);
                                holder.imageView_delete.setVisibility(View.VISIBLE);
                            }
                        });
            }
        });
    }


    @Override
    public int getItemCount() {
        return fishes.size();
    }

    public class FishViewHolder extends RecyclerView.ViewHolder {
        TextView textView_year, textView_month, textView_days, textView_maliat, textView_per_day, textView_whole_salary;
        public ImageView imageView_delete, imageView_print;

        public FishViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_year = itemView.findViewById(R.id.teacher_name_text);
            textView_month = itemView.findViewById(R.id.fish_month_text);
            textView_days = itemView.findViewById(R.id.teacher_degree_text);
            textView_maliat = itemView.findViewById(R.id.teacher_father_name);
            textView_per_day = itemView.findViewById(R.id.fish_per_day_text);
            textView_whole_salary = itemView.findViewById(R.id.fish_whole_salary);
            imageView_delete = itemView.findViewById(R.id.delete_fish_image);
            imageView_print = itemView.findViewById(R.id.print_fish_image);
        }
    }
}
