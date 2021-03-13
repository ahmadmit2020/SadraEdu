package com.am.android.sadraedu.Retrofit;

import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.Model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

//    @GET("public/shop_products")
//    fun getProducts(@Query("shop_id")shop_id : Int) : Call<List<Product>>

    @GET("getTeachers/")
    Call<List<Teacher>> getTeachers();

    @GET("getFishes/{teacher_id}")
    Call<List<Fish>> getTeacherFishes(@Path("teacher_id") int teacher_id);
}
