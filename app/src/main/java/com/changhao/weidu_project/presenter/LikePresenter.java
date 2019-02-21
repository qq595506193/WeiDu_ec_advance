package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ILikeContract;
import com.changhao.weidu_project.entity.LikeEntity;
import com.changhao.weidu_project.model.LikeModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class LikePresenter extends ILikeContract.LikePresenter {
    private LikeModel likeModel;
    private ILikeContract.ILikeView iLikeView;

    public LikePresenter(ILikeContract.ILikeView iLikeView) {
        likeModel = new LikeModel();
        this.iLikeView = iLikeView;
    }

    @Override
    public void getLike(HashMap<String, String> params) {
        likeModel.getLike(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                LikeEntity likeEntity = new Gson().fromJson(result, LikeEntity.class);
                if (iLikeView != null) {
                    iLikeView.onSuccess(likeEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iLikeView != null) {
                    iLikeView.onFailed(msg);
                }
            }
        });
    }
}
