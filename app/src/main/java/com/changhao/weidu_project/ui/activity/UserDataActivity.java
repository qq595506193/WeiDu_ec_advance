package com.changhao.weidu_project.ui.activity;

import android.net.Uri;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IUserDataContract;
import com.changhao.weidu_project.entity.UserDataEntity;
import com.changhao.weidu_project.presenter.UserDataPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDataActivity extends BaseActivity implements IUserDataContract.IUserDataView {
    @BindView(R.id.fresco_header)
    SimpleDraweeView fresco_header;
    @BindView(R.id.tv_my_name_right)
    TextView tv_my_name_right;
    @BindView(R.id.tv_my_pwd_right)
    TextView tv_my_pwd_right;
    private UserDataPresenter userDataPresenter;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        userDataPresenter.getUserData(params);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        userDataPresenter = new UserDataPresenter(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_user_data;
    }

    @Override
    public void onSuccess(UserDataEntity userDataEntity) {
        if (userDataEntity != null) {
            Uri uri = Uri.parse(userDataEntity.getResult().getHeadPic());
            fresco_header.setImageURI(uri);
            tv_my_name_right.setText(userDataEntity.getResult().getNickName());
            tv_my_pwd_right.setText(userDataEntity.getResult().getPassword());
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
