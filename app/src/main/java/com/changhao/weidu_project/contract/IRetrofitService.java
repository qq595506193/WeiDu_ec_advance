package com.changhao.weidu_project.contract;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IRetrofitService {

    @GET
    Call<ResponseBody> getReg(@Url String apiUrl);

    @POST
    Call<ResponseBody> postReg(@Url String apiUrl, @QueryMap HashMap<String, String> params);

}
