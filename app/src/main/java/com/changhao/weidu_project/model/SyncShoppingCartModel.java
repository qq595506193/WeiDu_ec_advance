package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.SyncShoppingCartApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ISyncShoppingCartContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class SyncShoppingCartModel implements ISyncShoppingCartContract.ISyncShoppingCartModel {
    @Override
    public void getIyncShopping(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doPut(SyncShoppingCartApi.SYNC_CART_URL, params, new IOkHttpCallback() {
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
