package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.HomeEntity;

import java.util.HashMap;
import java.util.List;

public interface IRxxpContract {
    abstract class RxxpPresenter {
        public abstract void getRxxp(HashMap<String, String> params);
    }

    interface IRxxpModel {
        void getRxxp(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IRxxpView {
        void onSuccess(List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> commodityListBeans);

        void onFailed(String msg);
    }
}
