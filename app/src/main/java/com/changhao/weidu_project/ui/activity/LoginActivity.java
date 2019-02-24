package com.changhao.weidu_project.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.ILoginContract;
import com.changhao.weidu_project.entity.LoginEntity;
import com.changhao.weidu_project.presenter.LoginPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.changhao.weidu_project.utils.RegularUtils;
import com.changhao.weidu_project.utils.RetrofitUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {

    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.icon_eye)
    ImageButton icon_eye;
    @BindView(R.id.ch_remember)
    CheckBox ch_remember;
    @BindView(R.id.tv_spReg)
    TextView tv_spReg;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_tpLogin)
    Button btn_tpLogin;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private LoginPresenter loginPresenter;

    @Override
    protected void initData() {
        boolean netWorkConnected = RetrofitUtils.getInstance().isNetWorkConnected(this);
        if (netWorkConnected == false) {
            Toast.makeText(this, "没网", Toast.LENGTH_SHORT).show();
        } else {
            initClick();
        }

    }

    /**
     * 点击事件
     */
    private void initClick() {

        icon_eye.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ed_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ed_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        // 登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ed_phone.getText().toString().trim();
                String pwd = ed_pwd.getText().toString().trim();
                if (!RegularUtils.isMobileExact(phone)) {
                    Toast.makeText(LoginActivity.this, "手机号不合法", Toast.LENGTH_SHORT).show();
                }
                if (phone.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                HashMap<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("pwd", pwd);
                loginPresenter.getLogin(params);

                if (ch_remember.isChecked()) {

                    editor.putString("phone", phone);
                    editor.putString("pwd", pwd);
                    editor.putBoolean("tiao", true);
                    editor.commit();
                } else {
                    editor.putBoolean("tiao", false);
                    editor.commit();
                }


            }
        });
        // 点击注册
        tv_spReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        // 点击QQ登录
        btn_tpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        sharedPreferences = getSharedPreferences("tiao", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean tiao = sharedPreferences.getBoolean("tiao", false);
        if (tiao) {
            String phone = sharedPreferences.getString("phone", null);
            String pwd = sharedPreferences.getString("pwd", null);
            ed_phone.setText(phone);
            ed_pwd.setText(pwd);
            ch_remember.setChecked(true);
        } else {
            editor.clear();
            ch_remember.setChecked(false);
            editor.commit();
        }
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginSuccess(LoginEntity loginEntity) {
        if (loginEntity.getStatus().equals("0000")) {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "手机号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
