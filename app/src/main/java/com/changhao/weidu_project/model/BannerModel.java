package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.BannerApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IBannerContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class BannerModel implements IBannerContract.IBannerModel {
    @Override
    public void getBanner(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doGet(BannerApi.API_BANNER, params, new IOkHttpCallback() {
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
