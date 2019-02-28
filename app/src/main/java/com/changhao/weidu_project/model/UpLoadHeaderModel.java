package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.UpLoadHeaderApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IUpLoadHeaderContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.io.File;
import java.util.HashMap;

public class UpLoadHeaderModel implements IUpLoadHeaderContract.IUpLoadHeaderModel {
    @Override
    public void getUpLoad(File file, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().upload(UpLoadHeaderApi.UP_LOAD_HEADER, file, new IOkHttpCallback() {
            @Override
            public void onSuccess(String result) {
                if (iRequestCallback != null) {
                    iRequestCallback.onSuccess(result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iRequestCallback != null) {
                    iRequestCallback.onFailed(msg);
                }
            }
        });
    }
}
