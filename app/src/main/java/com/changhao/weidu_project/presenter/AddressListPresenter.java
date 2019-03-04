package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IAddressListContract;
import com.changhao.weidu_project.entity.AddressListEntity;
import com.changhao.weidu_project.model.AddressListModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class AddressListPresenter extends IAddressListContract.AddressListPresenter {
    private AddressListModel addressListModel;
    private IAddressListContract.IAddressListView iAddressListView;

    public AddressListPresenter(IAddressListContract.IAddressListView iAddressListView) {
        addressListModel = new AddressListModel();
        this.iAddressListView = iAddressListView;
    }

    @Override
    public void getAddressList(HashMap<String, String> params) {
        addressListModel.getAddressList(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                AddressListEntity addressListEntity = new Gson().fromJson(result, AddressListEntity.class);
                if (iAddressListView != null) {
                    iAddressListView.onSuccess(addressListEntity.result);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iAddressListView != null) {
                    iAddressListView.onFailed(msg);
                }
            }
        });
    }
}
