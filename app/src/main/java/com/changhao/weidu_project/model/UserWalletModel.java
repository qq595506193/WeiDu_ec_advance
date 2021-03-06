package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.UserWalletApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IUserWalletContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class UserWalletModel implements IUserWalletContract.IUserWalletModel {
    @Override
    public void getUserWallet(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doGet(UserWalletApi.USER_WALLET_URL, params, new IOkHttpCallback() {
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
