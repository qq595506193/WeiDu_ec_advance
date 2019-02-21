package com.changhao.weidu_project.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends BaseFragment {


    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;
    @BindView(R.id.ck_qx)
    CheckBox ck_qx;
    @BindView(R.id.shopping_cart_rv)
    XRecyclerView shopping_cart_rv;
    @Override
    protected int getViewId() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);

        shopping_cart_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
