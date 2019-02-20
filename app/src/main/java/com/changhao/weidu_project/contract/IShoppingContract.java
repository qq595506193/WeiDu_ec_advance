package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.ShoppingEntity;

import java.util.HashMap;
import java.util.List;

public interface IShoppingContract {
    abstract class ShoppingPresenter {
        public abstract void getShopping(HashMap<String, String> params);

    }


    interface IShoppingMolde {
        void getShopping(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IShoppingView {
        void onShoppingSuccess(List<ShoppingEntity.Cart> carts);

        void onFailed(String msg);
    }


}
