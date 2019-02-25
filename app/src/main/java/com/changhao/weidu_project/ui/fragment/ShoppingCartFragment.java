package com.changhao.weidu_project.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.SearchShoppingAdapter;
import com.changhao.weidu_project.contract.ISearchShoppingContract;
import com.changhao.weidu_project.entity.SearchShoppingEntity;
import com.changhao.weidu_project.presenter.SearchShoppingPresenter;
import com.changhao.weidu_project.ui.base.BaseFragment;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends BaseFragment implements ISearchShoppingContract.ISearchShoppingView {


    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;
    @BindView(R.id.ck_qx)
    CheckBox ck_qx;
    @BindView(R.id.shopping_cart_rv)
    RecyclerView shopping_cart_rv;
    @BindView(R.id.shop_text_go)
    TextView shop_text_go;
    private SearchShoppingAdapter searchShoppingAdapter;
    private SearchShoppingPresenter searchShoppingPresenter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        searchShoppingPresenter.getSearchShopping(params);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);

        searchShoppingPresenter = new SearchShoppingPresenter(this);
        shopping_cart_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchShoppingAdapter = new SearchShoppingAdapter(getActivity());
        shopping_cart_rv.setAdapter(searchShoppingAdapter);

    }

    @Override
    public void onSuccess(List<SearchShoppingEntity.ResultBean> resultBeans) {
        if (resultBeans != null) {
            searchShoppingAdapter.setResultBeans(resultBeans);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
