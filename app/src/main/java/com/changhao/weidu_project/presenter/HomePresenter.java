package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IHomeContract;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.model.HomeModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class HomePresenter extends IHomeContract.HomePresenter {
    private HomeModel homeModel;
    private IHomeContract.IHomeView iHomeView;

    public HomePresenter(IHomeContract.IHomeView iHomeView) {
        homeModel = new HomeModel();
        this.iHomeView = iHomeView;
    }

    @Override
    public void getHome(HashMap<String, String> params) {
        homeModel.getHome(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result, HomeEntity.class);
                if (iHomeView != null) {
                    iHomeView.onRxxpHomeSuccess(homeEntity.getResult().getRxxp());
                    iHomeView.onPzshHomeSuccess(homeEntity.getResult().getPzsh());
                    iHomeView.onMlssHomeSuccess(homeEntity.getResult().getMlss());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iHomeView != null) {
                    iHomeView.onFailed(msg);
                }
            }
        });
    }
}
