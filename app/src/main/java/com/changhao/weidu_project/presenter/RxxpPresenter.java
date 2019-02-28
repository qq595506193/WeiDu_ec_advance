package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IRxxpContract;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.model.RxxpModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class RxxpPresenter extends IRxxpContract.RxxpPresenter {
    private RxxpModel rxxpModel;
    private IRxxpContract.IRxxpView iRxxpView;

    public RxxpPresenter(IRxxpContract.IRxxpView iRxxpView) {
        rxxpModel = new RxxpModel();
        this.iRxxpView = iRxxpView;
    }

    @Override
    public void getRxxp(HashMap<String, String> params) {
        rxxpModel.getRxxp(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result, HomeEntity.class);
                if (iRxxpView != null) {
                    iRxxpView.onSuccess(homeEntity.getResult().getRxxp().getCommodityList());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iRxxpView != null) {
                    iRxxpView.onFailed(msg);
                }
            }
        });
    }
}
