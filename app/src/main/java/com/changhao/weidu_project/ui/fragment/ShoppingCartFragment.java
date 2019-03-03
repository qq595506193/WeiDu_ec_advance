package com.changhao.weidu_project.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.SearchShoppingAdapter;
import com.changhao.weidu_project.callback.NotifyNum;
import com.changhao.weidu_project.contract.ISearchShoppingContract;
import com.changhao.weidu_project.entity.SearchShoppingEntity;
import com.changhao.weidu_project.presenter.SearchShoppingPresenter;
import com.changhao.weidu_project.ui.activity.OrderActivity;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends BaseFragment implements ISearchShoppingContract.ISearchShoppingView, NotifyNum {


    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;
    @BindView(R.id.ck_qx)
    CheckBox ck_qx;
    @BindView(R.id.shopping_cart_xrv)
    XRecyclerView shopping_cart_xrv;
    @BindView(R.id.shop_text_go)
    TextView shop_text_go;
    private SearchShoppingAdapter searchShoppingAdapter;
    private SearchShoppingPresenter searchShoppingPresenter;
    private List<SearchShoppingEntity.ResultBean> resultBeans;

    @Override
    protected int getViewId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        searchShoppingPresenter.getSearchShopping(params);
        initPresenter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        searchShoppingPresenter = new SearchShoppingPresenter(this);
        searchShoppingAdapter = new SearchShoppingAdapter(getActivity(), this);
        shopping_cart_xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopping_cart_xrv.setLoadingMoreEnabled(true);
        shopping_cart_xrv.setPullRefreshEnabled(true);
        shopping_cart_xrv.setAdapter(searchShoppingAdapter);
        shopping_cart_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initPresenter();
                shopping_cart_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                shopping_cart_xrv.loadMoreComplete();
            }
        });

        // 去结算
        shop_text_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<SearchShoppingEntity.ResultBean> list = new ArrayList<>();

                for (SearchShoppingEntity.ResultBean resultBean : resultBeans) {
                    if (resultBean.isCheck()) {
                        list.add(resultBean);
                    }

                }
                EventBus.getDefault().register(list);
                startActivity(new Intent(getActivity(), OrderActivity.class));
            }
        });
    }

    private void initPresenter() {


        ck_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ck_qx.isChecked()) {
                    for (SearchShoppingEntity.ResultBean resultBean : resultBeans) {
                        resultBean.setCheck(true);
                    }
                } else {
                    for (SearchShoppingEntity.ResultBean resultBean : resultBeans) {
                        resultBean.setCheck(false);
                    }
                }
                searchShoppingAdapter.notifyDataSetChanged();
                zPrice();
            }
        });


    }

    private void zPrice() {
        double total = 0;
        for (SearchShoppingEntity.ResultBean resultBean : resultBeans) {
            if (resultBean.isCheck()) {
                total += resultBean.getPrice() * resultBean.getNum();
            }
        }
        tv_totalPrice.setText(total + "");


    }

    @Override
    protected String getCacheDir() {
        return null;
    }

    @Override
    public void onSuccess(final List<SearchShoppingEntity.ResultBean> resultBeans) {
        if (resultBeans != null) {
            this.resultBeans = resultBeans;

            for (SearchShoppingEntity.ResultBean resultBean : this.resultBeans) {
                resultBean.setCheck(false);
            }

            searchShoppingAdapter.setResultBeans(this.resultBeans);


        }
    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void notifynum() {
        zPrice();
    }

    @Override
    public void isCheck(boolean check) {
        ck_qx.setChecked(check);
    }
}
