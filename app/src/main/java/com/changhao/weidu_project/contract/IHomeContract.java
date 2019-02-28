package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.HomeEntity;

import java.util.HashMap;

public interface IHomeContract {
    abstract class HomePresenter {
        public abstract void getHome(HashMap<String, String> params);
    }

    interface IHomeMdel {
        void getHome(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IHomeView {
        void onRxxpHomeSuccess(HomeEntity.ResultBean.RxxpBean rxxpBeans);

        void onMlssHomeSuccess(HomeEntity.ResultBean.MlssBean mlssBeans);

        void onPzshHomeSuccess(HomeEntity.ResultBean.PzshBean pzshBeans);

        void onFailed(String msg);
    }
}
