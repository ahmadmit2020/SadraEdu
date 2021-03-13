package com.am.android.sadraedu.Tools;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

import com.am.android.sadraedu.R;
import com.google.android.material.snackbar.Snackbar;


public class MySnackBar {




    public static void MySnackBarMargin(View view, Context context, int status_code, String text) {
        Snackbar snackbar =
                Snackbar.make(view, text, Snackbar.LENGTH_LONG);
//        if (top){
//            FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)snackbar.getView().getLayoutParams();
//            params.gravity = Gravity.TOP;
//            snackbar.getView().setLayoutParams(params);
//        }
        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        switch (status_code) {
            case 1:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_green);
                break;
            case 2:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_brown);
                break;
            case 3:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_yellow);

                break;
            default:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_green);

        }

        snackbar.getView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        TextView snack_text = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        snack_text.setTypeface(Typeface.createFromAsset(context.getAssets(), "iransans.ttf"));
        snack_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        snack_text.setTextColor(ContextCompat.getColor(context, R.color.white));

//        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
//        parentParams.setMargins(10, 10, 10, 10);
//        parentParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
//        parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        snackbar.getView().setLayoutParams(parentParams);

        snackbar.show();

    }

    public static void MySnackBarMarginButton(View view, Context context, int status_code, String text , String button , View.OnClickListener onClickListener) {
        Snackbar snackbar =
                Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        snackbar.setAction(button, onClickListener);
        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
        switch (status_code) {
            case 1:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_green);
                break;
            case 2:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_brown);
                break;
            case 3:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_yellow);

                break;
            default:
                snackbar.getView().setBackgroundResource(R.drawable.snackbar_background_green);

        }

        TextView snack_text = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_text);
        snack_text.setTypeface(Typeface.createFromAsset(context.getAssets(), "iransans.ttf"));
        snack_text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        snack_text.setTextColor(ContextCompat.getColor(context, R.color.white));


        TextView action = snackbar.getView().findViewById(com.google.android.material.R.id.snackbar_action);
        action.setTypeface(Typeface.createFromAsset(context.getAssets(), "iransans.ttf"));
        action.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        action.setTextColor(ContextCompat.getColor(context, R.color.white));

//        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
//        parentParams.setMargins(10, 10, 10, 10);
//        parentParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
//        parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
//        snackbar.getView().setLayoutParams(parentParams);

        snackbar.show();

    }



}
