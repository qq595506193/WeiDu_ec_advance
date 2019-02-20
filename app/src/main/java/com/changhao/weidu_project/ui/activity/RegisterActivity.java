package com.changhao.weidu_project.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IRegisterContract;
import com.changhao.weidu_project.entity.RegisterEntity;
import com.changhao.weidu_project.presenter.RegisterPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.changhao.weidu_project.utils.RegularUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements IRegisterContract.IRegisterView {
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;
    @BindView(R.id.tv_imLogin)
    TextView tv_imLogin;
    @BindView(R.id.btn_reg)
    Button btn_reg;
    private RegisterPresenter registerPresenter;


    @Override
    protected void initData() {

        initClick();
    }

    private void initClick() {
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ed_phone.getText().toString().trim();
                String pwd = ed_pwd.getText().toString().trim();
                if (!RegularUtils.isMobileExact(phone)) {
                    Toast.makeText(RegisterActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);
                registerPresenter.getRegister(params);
            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        registerPresenter = new RegisterPresenter(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_register;
    }


    @Override
    public void onRegisterSuccess(RegisterEntity registerEntity) {
        if (registerEntity.getStatus().equals("0000")) {
            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
