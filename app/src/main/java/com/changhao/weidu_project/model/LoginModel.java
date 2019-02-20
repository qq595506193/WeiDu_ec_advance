package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.LoginApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ILoginContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class LoginModel implements ILoginContract.ILoginModel {
    @Override
    public void getLogin(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPost(LoginApi.API_LOGIN, params, new IOkHttpCallback() {
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
