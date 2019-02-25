package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.SearchShoppingEntity;

import java.util.HashMap;
import java.util.List;

public interface ISearchShoppingContract {
    abstract class SearchShoppingPresenter {
        public abstract void getSearchShopping(HashMap<String, String> params);
    }

    interface ISearchShoppingModel {
        void getSearchShopping(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface ISearchShoppingView {
        void onSuccess(List<SearchShoppingEntity.ResultBean> resultBeans);

        void onFailed(String msg);
    }
}
