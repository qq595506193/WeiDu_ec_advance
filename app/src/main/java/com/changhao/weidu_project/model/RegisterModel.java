package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.RegisterApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IRegisterContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class RegisterModel implements IRegisterContract.IRegisterModel {
    @Override
    public void getRegister(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPost(RegisterApi.API_REGISTER, params, new IOkHttpCallback() {
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
