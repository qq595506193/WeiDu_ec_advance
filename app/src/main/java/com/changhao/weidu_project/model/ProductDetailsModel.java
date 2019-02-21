package com.changhao.weidu_project.model;

import com.changhao.weidu_project.apis.ProductDetailsApi;
import com.changhao.weidu_project.callback.IOkHttpCallback;
import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IProductDetailsContract;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

public class ProductDetailsModel implements IProductDetailsContract.IProductDetailsModel {
    @Override
    public void getProductDetails(HashMap<String, String> params, final IRequestCallback iRequestCallback) {
        RetrofitUtils.getInstance().doGet(ProductDetailsApi.PRODUCT_DETAILS_URL, params, new IOkHttpCallback() {
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
