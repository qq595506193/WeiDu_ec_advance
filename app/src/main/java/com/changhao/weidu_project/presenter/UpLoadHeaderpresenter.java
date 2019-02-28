package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IUpLoadHeaderContract;
import com.changhao.weidu_project.entity.UpLoadHeaderEntity;
import com.changhao.weidu_project.model.UpLoadHeaderModel;
import com.google.gson.Gson;

import java.io.File;

public class UpLoadHeaderpresenter extends IUpLoadHeaderContract.UpLoadHeaderPresenter {
    private UpLoadHeaderModel upLoadHeaderModel;
    private IUpLoadHeaderContract.IUpLoadHeaderView iUpLoadHeaderView;

    public UpLoadHeaderpresenter(IUpLoadHeaderContract.IUpLoadHeaderView iUpLoadHeaderView) {
        upLoadHeaderModel = new UpLoadHeaderModel();
        this.iUpLoadHeaderView = iUpLoadHeaderView;
    }

    @Override
    public void getUpLoad(File file) {
        upLoadHeaderModel.getUpLoad(file, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                UpLoadHeaderEntity upLoadHeaderEntity = new Gson().fromJson(result, UpLoadHeaderEntity.class);
                if (iUpLoadHeaderView != null) {
                    iUpLoadHeaderView.onSuccess(upLoadHeaderEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iUpLoadHeaderView != null) {
                    iUpLoadHeaderView.onFailed(msg);
                }
            }
        });
    }
}
