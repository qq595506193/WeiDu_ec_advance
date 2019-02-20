package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ILoginContract;
import com.changhao.weidu_project.entity.LoginEntity;
import com.changhao.weidu_project.model.LoginModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class LoginPresenter extends ILoginContract.LoginPresenter {
    private LoginModel loginModel;
    private ILoginContract.ILoginView iLoginView;

    public LoginPresenter(ILoginContract.ILoginView iLoginView) {
        loginModel = new LoginModel();
        this.iLoginView = iLoginView;
    }

    @Override
    public void getLogin(HashMap<String, String> params) {
        loginModel.getLogin(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                LoginEntity loginEntity = new Gson().fromJson(result, LoginEntity.class);
                if (iLoginView != null) {
                    iLoginView.onLoginSuccess(loginEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iLoginView != null) {
                    iLoginView.onFailed(msg);
                }
            }
        });
    }
}
