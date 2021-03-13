package com.am.android.sadraedu.Retrofit;

import com.am.android.sadraedu.Model.CreateFishResponse;
import com.am.android.sadraedu.Model.Fish;
import com.am.android.sadraedu.Model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

//    @GET("public/shop_products")
//    fun getProducts(@Query("shop_id")shop_id : Int) : Call<List<Product>>

    @GET("getTeachers/")
    Call<List<Teacher>> getTeachers();

    @GET("getFishes/{teacher_id}")
    Call<List<Fish>> getTeacherFishes(@Path("teacher_id") int teacher_id);

    @Multipart
    @POST("createFishes/")
    Call<CreateFishResponse> addFish(
            @Part("teacher_id") int teacher_id,
            @Part("mounth") int mounth,
            @Part("working_days") int working_days,
            @Part("m_percent") int m_percent,
            @Part("per_salary") int per_salary,
            @Part("year") int year
    );


    @DELETE("deleteFishes/{fish_id}")
    Call<String> deleteFish(@Path("fish_id") int fish_id);
}
