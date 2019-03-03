package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IPzshContract;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.model.PzshModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class PzshPresenter extends IPzshContract.PzshPresenter {
    private PzshModel pzshModel;
    private IPzshContract.IPzshView iPzshView;

    public PzshPresenter(IPzshContract.IPzshView iPzshView) {
        pzshModel = new PzshModel();
        this.iPzshView = iPzshView;
    }

    @Override
    public void getPzsh(HashMap<String, String> params) {
        pzshModel.getPzsh(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result, HomeEntity.class);
                if (iPzshView != null) {
                    iPzshView.onSuccess(homeEntity.getResult().getPzsh().getCommodityList());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iPzshView != null) {
                    iPzshView.onFailed(msg);
                }
            }
        });
    }
}
