package com.changhao.weidu_project.utils;

import com.changhao.weidu_project.apis.Api;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.contract.IRetrofitService;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instance;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    private RetrofitUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Api.BASE_URL)
                .client(okHttpClient)
                .build();
    }

    // 单例双重检验锁
    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public void doGet(String apiUrl, HashMap<String, String> parmas, final IOkHttpCallback iOkHttpCallback) {
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        retrofitService.getReg(apiUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        String result = response.body().string();
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onSuccess(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (iOkHttpCallback != null) {
                    iOkHttpCallback.onFailed("网络不稳定");
                }
            }
        });
    }

    public void doPost(String apiUrl, HashMap<String, String> parmas, final IOkHttpCallback iOkHttpCallback) {
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        retrofitService.postReg(apiUrl, parmas).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        String result = response.body().string();
                        if (iOkHttpCallback != null) {
                            iOkHttpCallback.onSuccess(result);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (iOkHttpCallback != null) {
                    iOkHttpCallback.onFailed("网络不稳定");
                }
            }
        });
    }

}
