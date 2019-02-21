package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.LikeApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ILikeContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class LikeModel implements ILikeContract.ILikeModel {
    @Override
    public void getLike(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPost(LikeApi.LIKE_URL, params, new IOkHttpCallback() {
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
