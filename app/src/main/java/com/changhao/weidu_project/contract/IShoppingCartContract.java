package com.changhao.weidu_project.contract;

import com.changhao.weidu_project.callback.IRequestCallback;
import com.changhao.weidu_project.entity.ShoppingCartEntity;

import java.util.HashMap;
import java.util.List;

public interface IShoppingCartContract {
    abstract class ShoppingCartPresenter {
        public abstract void getShoppingCart(HashMap<String, String> params);

    }


    interface IShoppingCartMolde {
        void getShoppingCart(HashMap<String, String> params, IRequestCallback iRequestCallback);
    }

    interface IShoppingCartView {
        void onSuccess(List<ShoppingCartEntity.Cart> carts);

        void onFailed(String msg);
    }


}
