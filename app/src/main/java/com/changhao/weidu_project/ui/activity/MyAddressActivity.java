package com.changhao.weidu_project.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.AddressListAdapter;
import com.changhao.weidu_project.contract.IAddressListContract;
import com.changhao.weidu_project.entity.AddressListEntity;
import com.changhao.weidu_project.presenter.AddressListPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAddressActivity extends BaseActivity implements IAddressListContract.IAddressListView {

    @BindView(R.id.tv_my_address_over)
    TextView tv_my_address_over;
    @BindView(R.id.xrv_my_address)
    XRecyclerView xrv_my_address;
    @BindView(R.id.btn_add_address)
    Button btn_add_address;
    private AddressListPresenter addressListPresenter;
    private AddressListAdapter addressListAdapter;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        addressListPresenter.getAddressList(params);


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        addressListPresenter = new AddressListPresenter(this);
        addressListAdapter = new AddressListAdapter(this);
        xrv_my_address.setLayoutManager(new LinearLayoutManager(this));
        xrv_my_address.setAdapter(addressListAdapter);
        xrv_my_address.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
                xrv_my_address.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrv_my_address.loadMoreComplete();
            }
        });

        tv_my_address_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAddressActivity.this, InsertAddressActivity.class));
            }
        });
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_my_address;
    }

    @Override
    public void onSuccess(List<AddressListEntity.Result> results) {
        if (results != null) {
            addressListAdapter.setResults(results);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
