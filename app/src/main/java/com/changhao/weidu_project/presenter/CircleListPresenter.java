package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ICircleListContract;
import com.changhao.weidu_project.entity.CircleListEntity;
import com.changhao.weidu_project.model.CircleListModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class CircleListPresenter extends ICircleListContract.CircleListPresenter {
    private CircleListModel circleListModel;
    private ICircleListContract.ICircleListView iCircleListView;

    public CircleListPresenter(ICircleListContract.ICircleListView iCircleListView) {
        circleListModel = new CircleListModel();
        this.iCircleListView = iCircleListView;
    }

    @Override
    public void getCircleList(HashMap<String, String> params) {
        circleListModel.getCircleList(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                CircleListEntity circleListEntity = new Gson().fromJson(result, CircleListEntity.class);
                if (iCircleListView != null) {
                    iCircleListView.onSuccess(circleListEntity.getResult());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iCircleListView != null) {
                    iCircleListView.onFailed(msg);
                }
            }
        });
    }
}
