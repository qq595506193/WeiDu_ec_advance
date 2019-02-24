package com.changhao.weidu_project.presenter;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.contract.IProductDetailsContract;
import com.changhao.weidu_project.entity.ProductDetailsEntity;
import com.changhao.weidu_project.model.ProductDetailsModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class ProductDetailsPresenter extends IProductDetailsContract.ProductDetailsPresenter {
    private ProductDetailsModel productDetailsModel;
    private IProductDetailsContract.IProductDetailsView iProductDetailsView;

    public ProductDetailsPresenter(IProductDetailsContract.IProductDetailsView iProductDetailsView) {
        productDetailsModel = new ProductDetailsModel();
        this.iProductDetailsView = iProductDetailsView;
    }

    @Override
    public void getProductDetails(HashMap<String, String> params) {
        productDetailsModel.getProductDetails(params, new IRequestCallback() {
            @Override
            public void onSuccess(String result) {
                ProductDetailsEntity productDetailsEntity = new Gson().fromJson(result, ProductDetailsEntity.class);
                if (iProductDetailsView != null) {
                    iProductDetailsView.onSuccess(productDetailsEntity);
                }
            }

            @Override
            public void onFailed(String msg) {
                if (iProductDetailsView != null) {
                    iProductDetailsView.onFailed(msg);
                }
            }
        });
    }
}
