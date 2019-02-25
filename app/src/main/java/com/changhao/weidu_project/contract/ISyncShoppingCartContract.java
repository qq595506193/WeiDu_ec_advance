package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.SyncShoppingCartEntity;

import java.util.HashMap;

public interface ISyncShoppingCartContract {
    abstract class SyncShoppingCartPresenter {
        public abstract void getSyncShopping(HashMap<String, String> params);
    }

    interface ISyncShoppingCartModel {
        void getIyncShopping(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface ISyncShoppingCartView {
        void onSuccess(SyncShoppingCartEntity syncShoppingCartEntity);

        void onFailed(String msg);
    }
}
