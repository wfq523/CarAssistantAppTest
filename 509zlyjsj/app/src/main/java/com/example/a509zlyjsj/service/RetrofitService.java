package com.example.a509zlyjsj.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitService {

    @GET
    Call<ResponseBody> getPdf(@Url String url);
}