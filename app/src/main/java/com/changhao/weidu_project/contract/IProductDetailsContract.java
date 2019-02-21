package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.ProductDetailsEntity;

import java.util.HashMap;
import java.util.List;

public interface IProductDetailsContract {
    abstract class ProductDetailsPresenter {
        public abstract void getProductDetails(HashMap<String,String> params);
    }

    interface IProductDetailsModel {
        void getProductDetails(HashMap<String,String> params, IRequestCallback iRequestCallback);
    }

    interface IProductDetailsView {
        void onSuccess(List<ProductDetailsEntity.ResultBean> productResultBeans);

        void onFailed(String msg);
    }
}
