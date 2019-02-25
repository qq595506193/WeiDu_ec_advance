package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ISyncShoppingCartContract;
import com.changhao.weidu_project.entity.SyncShoppingCartEntity;
import com.changhao.weidu_project.model.SyncShoppingCartModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class SyncShoppingCartPresenter extends ISyncShoppingCartContract.SyncShoppingCartPresenter {
    private SyncShoppingCartModel syncShoppingCartModel;
    private ISyncShoppingCartContract.ISyncShoppingCartView iSyncShoppingCartView;

    public SyncShoppingCartPresenter(ISyncShoppingCartContract.ISyncShoppingCartView iSyncShoppingCartView) {
        syncShoppingCartModel = new SyncShoppingCartModel();
        this.iSyncShoppingCartView = iSyncShoppingCartView;
    }

    @Override
    public void getSyncShopping(HashMap<String, String> params) {
        syncShoppingCartModel.getIyncShopping(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                SyncShoppingCartEntity syncShoppingCartEntity = new Gson().fromJson(result, SyncShoppingCartEntity.class);
                if (iSyncShoppingCartView != null) {
                    iSyncShoppingCartView.onSuccess(syncShoppingCartEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iSyncShoppingCartView != null) {
                    iSyncShoppingCartView.onFailed(msg);
                }
            }
        });
    }
}
