package com.changhao.weidu_project.callback;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface IRetrofitService {

    @GET
    Observable<ResponseBody> getReg(@Url String apiUrl, @QueryMap HashMap<String, String> params);

    @POST
    Observable<ResponseBody> postReg(@Url String apiUrl, @QueryMap HashMap<String, String> params);

    @PUT
    Observable<ResponseBody> putReg(@Url String apiUrl, @QueryMap HashMap<String, String> params);

    @POST
    @Multipart
    Observable<ResponseBody> upload(@Url String apiUrl, @Part File part);

}
