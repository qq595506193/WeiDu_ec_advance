package com.changhao.weidu_project.interceptor;

import com.changhao.weidu_project.app.App;
import com.changhao.weidu_project.utils.SpUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        builder.addHeader("userId",SpUtil.getString(App.context,"userId",""));
        builder.addHeader("sessionId",SpUtil.getString(App.context,"sessionId",""));
        Request build = builder.build();
        Response response = chain.proceed(build);
        return response;
    }
}
