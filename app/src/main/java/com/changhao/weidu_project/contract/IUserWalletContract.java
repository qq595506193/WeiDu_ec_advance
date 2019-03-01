package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.UserWalletEntity;

import java.util.HashMap;

public interface IUserWalletContract {
    abstract class UserWalletPresenter {
        public abstract void getUserWallet(HashMap<String, String> params);
    }

    interface IUserWalletModel {
        void getUserWallet(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IUserWalletView {
        void onSuccess(UserWalletEntity userWalletEntity);

        void onFailed(String msg);
    }
}
