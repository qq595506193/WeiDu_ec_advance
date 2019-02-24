package com.changhao.weidu_project.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.ProductDetailsAdapter;
import com.changhao.weidu_project.contract.IProductDetailsContract;
import com.changhao.weidu_project.entity.ProductDetailsEntity;
import com.changhao.weidu_project.presenter.ProductDetailsPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity implements IProductDetailsContract.IProductDetailsView {

    @BindView(R.id.tv_left_back)
    TextView tv_left_back;
    @BindView(R.id.details_xrv)
    XRecyclerView details_xrv;
    private ProductDetailsPresenter productDetailsPresenter;
    private ProductDetailsAdapter productDetailsAdapter;
    private String itemId;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("commodityId", itemId);
        productDetailsPresenter.getProductDetails(params);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        productDetailsPresenter = new ProductDetailsPresenter(this);
        details_xrv.setLayoutManager(new LinearLayoutManager(this));
        productDetailsAdapter = new ProductDetailsAdapter(this);
        details_xrv.setAdapter(productDetailsAdapter);

        details_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                details_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                details_xrv.loadMoreComplete();
            }
        });

        tv_left_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void onSuccess(ProductDetailsEntity productResultBeans) {
        if (productResultBeans != null) {
            productDetailsAdapter.setProductDetailsEntity(productResultBeans);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
