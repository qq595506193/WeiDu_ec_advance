package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ISearchShoppingContract;
import com.changhao.weidu_project.entity.SearchShoppingEntity;
import com.changhao.weidu_project.model.SearchShoppingModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class SearchShoppingPresenter extends ISearchShoppingContract.SearchShoppingPresenter {
    private SearchShoppingModel searchShoppingModel;
    private ISearchShoppingContract.ISearchShoppingView iSearchShoppingView;

    public SearchShoppingPresenter(ISearchShoppingContract.ISearchShoppingView iSearchShoppingView) {
        searchShoppingModel = new SearchShoppingModel();
        this.iSearchShoppingView = iSearchShoppingView;
    }

    @Override
    public void getSearchShopping(HashMap<String, String> params) {
        searchShoppingModel.getSearchShopping(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                SearchShoppingEntity searchShoppingEntity = new Gson().fromJson(result, SearchShoppingEntity.class);
                if (iSearchShoppingView != null) {
                    iSearchShoppingView.onSuccess(searchShoppingEntity.getResult());
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iSearchShoppingView != null) {
                    iSearchShoppingView.onFailed(msg);
                }
            }
        });
    }
}
