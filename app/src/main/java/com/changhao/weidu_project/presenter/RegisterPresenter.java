package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IRegisterContract;
import com.changhao.weidu_project.entity.RegisterEntity;
import com.changhao.weidu_project.model.RegisterModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class RegisterPresenter extends IRegisterContract.RegisterPresenter {
    private RegisterModel registerModel;
    private IRegisterContract.IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterContract.IRegisterView iRegisterView) {
        registerModel = new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void getRegister(HashMap<String, String> params) {
        registerModel.getRegister(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                RegisterEntity registerEntity = new Gson().fromJson(result, RegisterEntity.class);
                if (iRegisterView != null) {
                    iRegisterView.onRegisterSuccess(registerEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iRegisterView != null) {
                    iRegisterView.onFailed(msg);
                }
            }
        });
    }
}
