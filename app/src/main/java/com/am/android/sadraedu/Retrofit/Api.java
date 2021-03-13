package com.am.android.sadraedu.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    final static String BaseUrl = "https://sadraedu.com/admin/api/";
    public static Retrofit retrofit = null;

    public static Retrofit api() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
