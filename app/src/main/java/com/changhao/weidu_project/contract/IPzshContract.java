package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.HomeEntity;

import java.util.HashMap;
import java.util.List;

public interface IPzshContract {
    abstract class PzshPresenter {
        public abstract void getPzsh(HashMap<String, String> params);
    }

    interface IPzshModel {
        void getPzsh(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IPzshView {
        void onSuccess(List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXES);

        void onFailed(String msg);
    }
}
