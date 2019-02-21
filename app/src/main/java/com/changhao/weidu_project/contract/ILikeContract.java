package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.LikeEntity;

import java.util.HashMap;

public interface ILikeContract {
    abstract class LikePresenter {
        public abstract void getLike(HashMap<String,String> params);
    }
    interface ILikeModel {
        void getLike(HashMap<String,String> params, IRequestCallback iRequestCallback);
    }

    interface ILikeView {
        void onSuccess(LikeEntity likeEntity);

        void onFailed(String msg);
    }
}
