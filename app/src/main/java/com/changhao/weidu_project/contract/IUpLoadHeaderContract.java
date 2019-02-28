package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.UpLoadHeaderEntity;

import java.io.File;
import java.util.HashMap;

public interface IUpLoadHeaderContract {
    abstract class UpLoadHeaderPresenter {
        public abstract void getUpLoad(File file);
    }

    interface IUpLoadHeaderModel {
        void getUpLoad(File file, IRequestCallback iRequestCallback);
    }

    interface IUpLoadHeaderView {
        void onSuccess(UpLoadHeaderEntity upLoadHeaderEntity);

        void onFailed(String msg);
    }
}
