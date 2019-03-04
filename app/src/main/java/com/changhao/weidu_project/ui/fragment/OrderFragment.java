package com.changhao.weidu_project.ui.fragment;

import android.view.View;
import android.widget.ImageView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends BaseFragment {

    @BindView(R.id.bill_image_allbill)
    ImageView bill_image_allbill;
    @BindView(R.id.bill_image_pay)
    ImageView bill_image_pay;
    @BindView(R.id.bill_image_task)
    ImageView bill_image_task;
    @BindView(R.id.bill_image_appraise)
    ImageView bill_image_appraise;
    @BindView(R.id.bill_image_finish)
    ImageView bill_image_finish;
    @BindView(R.id.bill_xrecy)
    XRecyclerView bill_xrecy;


    @Override
    protected int getViewId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected String getCacheDir() {
        return null;
    }
}
