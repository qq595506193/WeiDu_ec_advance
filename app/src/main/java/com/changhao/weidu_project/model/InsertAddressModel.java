package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.InsertAddressApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IInsertAddressContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class InsertAddressModel implements IInsertAddressContract.IInsertAddressModel {
    @Override
    public void getInsertAddress(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPost(InsertAddressApi.INSERT_ADDRESS_URL, params, new IOkHttpCallback() {
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
