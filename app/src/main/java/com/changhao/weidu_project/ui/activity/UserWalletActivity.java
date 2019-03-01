package com.changhao.weidu_project.ui.activity;

import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IUserWalletContract;
import com.changhao.weidu_project.entity.UserWalletEntity;
import com.changhao.weidu_project.presenter.UserWalletPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserWalletActivity extends BaseActivity implements IUserWalletContract.IUserWalletView {
    @BindView(R.id.tv_banlance_count)
    TextView tv_banlance_count;
    @BindView(R.id.wallet_xrv)
    XRecyclerView wallet_xrv;
    private UserWalletPresenter userWalletPresenter;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        userWalletPresenter.getUserWallet(params);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        userWalletPresenter = new UserWalletPresenter(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_user_wallet;
    }

    @Override
    public void onSuccess(UserWalletEntity userWalletEntity) {
        if (userWalletEntity != null) {
            tv_banlance_count.setText(userWalletEntity.getResult().getBalance() + "");
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
