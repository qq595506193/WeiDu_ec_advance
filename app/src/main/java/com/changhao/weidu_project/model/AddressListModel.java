package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.AddressListApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IAddressListContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class AddressListModel implements IAddressListContract.IAddressListModel {
    @Override
    public void getAddressList(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPost(AddressListApi.ADDRESS_URL, params, new IOkHttpCallback() {
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
