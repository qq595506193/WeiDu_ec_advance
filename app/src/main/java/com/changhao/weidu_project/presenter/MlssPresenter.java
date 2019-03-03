package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IMlssContract;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.model.MlssModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class MlssPresenter extends IMlssContract.MlssPresenter {
    private MlssModel mlssModel;
    private IMlssContract.IMlssView iMlssView;

    public MlssPresenter(IMlssContract.IMlssView iMlssView) {
        mlssModel = new MlssModel();
        this.iMlssView = iMlssView;
    }

    @Override
    public void getMlss(HashMap<String, String> params) {
        mlssModel.getMlss(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                HomeEntity homeEntity = new Gson().fromJson(result, HomeEntity.class);
                if (iMlssView != null) {
                    iMlssView.onSuccess(homeEntity.getResult().getMlss().getCommodityList());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iMlssView != null) {
                    iMlssView.onFailed(msg);
                }
            }
        });
    }
}
