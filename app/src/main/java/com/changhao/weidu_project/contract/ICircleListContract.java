package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.CircleListEntity;

import java.util.HashMap;
import java.util.List;

public interface ICircleListContract {
    abstract class CircleListPresenter {
        public abstract void getCircleList(HashMap<String, String> params);
    }

    interface ICircleListModel {
        void getCircleList(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface ICircleListView {
        void onSuccess(List<CircleListEntity.ResultBean> circleResultBeans);

        void onFailed(String msg);
    }
}
