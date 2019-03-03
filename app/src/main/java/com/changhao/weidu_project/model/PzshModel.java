package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.HomeApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IPzshContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class PzshModel implements IPzshContract.IPzshModel {
    @Override
    public void getPzsh(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doGet(HomeApi.API_HOME, params, new IOkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (iRequestCallback != null) {
                    iRequestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iRequestCallback != null) {
                    iRequestCallback.onFailed(msg);
                }
            }
        });
    }
}
