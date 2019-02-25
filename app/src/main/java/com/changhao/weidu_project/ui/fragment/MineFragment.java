package com.changhao.weidu_project.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {

    @BindView(R.id.my_image_header)
    SimpleDraweeView my_image_header;
    @BindView(R.id.my_text_name)
    TextView my_text_name;
    @BindView(R.id.my_text_personal)
    TextView my_text_personal;
    @BindView(R.id.my_text_circle)
    TextView my_text_circle;
    @BindView(R.id.my_text_foot)
    TextView my_text_foot;
    @BindView(R.id.my_text_wallet)
    TextView my_text_wallet;
    @BindView(R.id.my_text_address)
    TextView my_text_address;


    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }
}
