package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.InsertAddressEntity;

import java.util.HashMap;

public interface IInsertAddressContract {
    abstract class InsertAddressPresenter {
        public abstract void getInsertAddress(HashMap<String, String> params);
    }

    interface IInsertAddressModel {
        void getInsertAddress(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IInsertAddressView {
        void onSuccess(InsertAddressEntity insertAddressEntity);

        void onFailed(String msg);
    }
}
