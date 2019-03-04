package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IInsertAddressContract;
import com.changhao.weidu_project.entity.InsertAddressEntity;
import com.changhao.weidu_project.model.InsertAddressModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class InsertAddressPresenter extends IInsertAddressContract.InsertAddressPresenter {
    private InsertAddressModel insertAddressModel;
    private IInsertAddressContract.IInsertAddressView iInsertAddressView;

    public InsertAddressPresenter(IInsertAddressContract.IInsertAddressView iInsertAddressView) {
        insertAddressModel = new InsertAddressModel();
        this.iInsertAddressView = iInsertAddressView;
    }

    @Override
    public void getInsertAddress(HashMap<String, String> params) {
        insertAddressModel.getInsertAddress(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                InsertAddressEntity insertAddressEntity = new Gson().fromJson(result, InsertAddressEntity.class);
                if (iInsertAddressView != null) {
                    iInsertAddressView.onSuccess(insertAddressEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iInsertAddressView != null) {
                    iInsertAddressView.onFailed(msg);
                }
            }
        });
    }
}
