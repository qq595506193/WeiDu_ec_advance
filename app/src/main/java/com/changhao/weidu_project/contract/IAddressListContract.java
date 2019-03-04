package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.AddressListEntity;

import java.util.HashMap;
import java.util.List;

public interface IAddressListContract {
    abstract class AddressListPresenter {
        public abstract void getAddressList(HashMap<String, String> params);
    }

    interface IAddressListModel {
        void getAddressList(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IAddressListView {
        void onSuccess(List<AddressListEntity.Result> results);

        void onFailed(String msg);
    }
}
