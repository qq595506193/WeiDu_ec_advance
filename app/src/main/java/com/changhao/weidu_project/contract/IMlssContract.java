package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.HomeEntity;

import java.util.HashMap;
import java.util.List;

public interface IMlssContract {
    abstract class MlssPresenter {
        public abstract void getMlss(HashMap<String, String> params);
    }

    interface IMlssModel {
        void getMlss(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IMlssView {
        void onSuccess(List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES);

        void onFailed(String msg);
    }

}
