package com.changhao.weidu_project.ui.activity;

import android.content.Intent;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IProductDetailsContract;
import com.changhao.weidu_project.entity.ProductDetailsEntity;
import com.changhao.weidu_project.ui.base.BaseActivity;

public class ProductDetailsActivity extends BaseActivity implements IProductDetailsContract.IProductDetailsView {
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String itemId = intent.getStringExtra("itemId");
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void onSuccess(ProductDetailsEntity.ResultBean productResultBeans) {

    }

    @Override
    public void onFailed(String msg) {

    }
}
