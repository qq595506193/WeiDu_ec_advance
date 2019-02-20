package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.BannerEntity;

import java.util.HashMap;

public interface IBannerContract {
    abstract class BannerPresenter {
        public abstract void getBanner(HashMap<String, String> params);
    }

    interface IBannerModel {
        void getBanner(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IBannerView {
        void onSuccess(BannerEntity bannerEntity);

        void onFailed(String msg);
    }
}
