package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IBannerContract;
import com.changhao.weidu_project.entity.BannerEntity;
import com.changhao.weidu_project.model.BannerModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class BannerPresenter extends IBannerContract.BannerPresenter {
    private BannerModel bannerModel;
    private IBannerContract.IBannerView iBannerView;

    public BannerPresenter(IBannerContract.IBannerView iBannerView) {
        bannerModel = new BannerModel();
        this.iBannerView = iBannerView;
    }

    @Override
    public void getBanner(HashMap<String, String> params) {
        bannerModel.getBanner(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                BannerEntity bannerEntity = new Gson().fromJson(result, BannerEntity.class);
                if (iBannerView != null) {
                    iBannerView.onSuccess(bannerEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iBannerView != null) {
                    iBannerView.onFailed(msg);
                }
            }
        });
    }
}
