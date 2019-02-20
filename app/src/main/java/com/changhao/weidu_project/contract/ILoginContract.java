package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.LoginEntity;

import java.util.HashMap;

public interface ILoginContract {
    abstract class LoginPresenter {
        public abstract void getLogin(HashMap<String, String> params);
    }

    interface ILoginModel {
        void getLogin(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface ILoginView {
        void onLoginSuccess(LoginEntity loginEntity);

        void onFailed(String msg);
    }
}
