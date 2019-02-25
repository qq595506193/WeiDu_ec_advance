package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.SearchShoppingApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.ISearchShoppingContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class SearchShoppingModel implements ISearchShoppingContract.ISearchShoppingModel {
    @Override
    public void getSearchShopping(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doGet(SearchShoppingApi.SEARCH_SHOPPING_URL, params, new IOkHttpCallback() {
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
