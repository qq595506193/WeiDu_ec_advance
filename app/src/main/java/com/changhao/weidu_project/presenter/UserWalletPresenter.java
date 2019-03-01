package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IUserWalletContract;
import com.changhao.weidu_project.entity.UserWalletEntity;
import com.changhao.weidu_project.model.UserWalletModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class UserWalletPresenter extends IUserWalletContract.UserWalletPresenter {
    private UserWalletModel userWalletModel;
    private IUserWalletContract.IUserWalletView iUserWalletView;

    public UserWalletPresenter(IUserWalletContract.IUserWalletView iUserWalletView) {
        userWalletModel = new UserWalletModel();
        this.iUserWalletView = iUserWalletView;
    }

    @Override
    public void getUserWallet(HashMap<String, String> params) {
        userWalletModel.getUserWallet(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                UserWalletEntity userWalletEntity = new Gson().fromJson(result, UserWalletEntity.class);
                if (iUserWalletView != null) {
                    iUserWalletView.onSuccess(userWalletEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iUserWalletView != null) {
                    iUserWalletView.onFailed(msg);
                }
            }
        });
    }
}
