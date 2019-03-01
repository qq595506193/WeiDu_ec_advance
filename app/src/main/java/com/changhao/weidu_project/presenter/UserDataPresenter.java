package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IUserDataContract;
import com.changhao.weidu_project.entity.UserDataEntity;
import com.changhao.weidu_project.model.UserDataModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class UserDataPresenter extends IUserDataContract.UserDataPresenter {

    private UserDataModel userDataModel;
    private IUserDataContract.IUserDataView iUserDataView;

    public UserDataPresenter(IUserDataContract.IUserDataView iUserDataView) {
        userDataModel = new UserDataModel();
        this.iUserDataView = iUserDataView;
    }

    @Override
    public void getUserData(HashMap<String, String> params) {
        userDataModel.getUserData(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                UserDataEntity userDataEntity = new Gson().fromJson(result, UserDataEntity.class);
                if (iUserDataView != null) {
                    iUserDataView.onSuccess(userDataEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iUserDataView != null) {
                    iUserDataView.onFailed(msg);
                }
            }
        });
    }
}
