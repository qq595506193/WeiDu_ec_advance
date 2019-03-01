package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.UserDataEntity;

import java.util.HashMap;

public interface IUserDataContract {
    abstract class UserDataPresenter {
        public abstract void getUserData(HashMap<String, String> params);
    }

    interface IUserDataModel {
        void getUserData(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IUserDataView {
        void onSuccess(UserDataEntity userDataEntity);

        void onFailed(String msg);
    }
}
