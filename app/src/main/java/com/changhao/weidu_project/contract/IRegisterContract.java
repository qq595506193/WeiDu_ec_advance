package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.RegisterEntity;

import java.util.HashMap;

public interface IRegisterContract {
    abstract class RegisterPresenter {
        public abstract void getRegister(HashMap<String, String> params);
    }

    interface IRegisterModel {
        void getRegister(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IRegisterView {
        void onRegisterSuccess(RegisterEntity registerEntity);

        void onFailed(String msg);
    }
}
