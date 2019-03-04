package com.changhao.weidu_project.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IInsertAddressContract;
import com.changhao.weidu_project.entity.InsertAddressEntity;
import com.changhao.weidu_project.presenter.InsertAddressPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InsertAddressActivity extends BaseActivity implements IInsertAddressContract.IInsertAddressView {
    @BindView(R.id.new_address_edit_name)
    EditText new_address_edit_name;
    @BindView(R.id.new_address_edit_phone)
    EditText new_address_edit_phone;
    @BindView(R.id.new_address_edit_address)
    EditText new_address_edit_address;
    @BindView(R.id.new_address_edit_zipCode)
    EditText new_address_edit_zipCode;
    @BindView(R.id.new_address_text_save)
    Button new_address_text_save;
    private InsertAddressPresenter insertAddressPresenter;

    @Override
    protected void initData() {
        final HashMap<String, String> params = new HashMap<>();


        new_address_text_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = new_address_edit_name.getText().toString().trim();
                String phone = new_address_edit_phone.getText().toString().trim();
                String address = new_address_edit_address.getText().toString().trim();
                String zipCode = new_address_edit_zipCode.getText().toString().trim();

                params.put("realName", name);
                params.put("phone", phone);
                params.put("address", address);
                params.put("zipCode", zipCode);

                insertAddressPresenter.getInsertAddress(params);


            }
        });
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        insertAddressPresenter = new InsertAddressPresenter(this);

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_insert_address;
    }

    @Override
    public void onSuccess(InsertAddressEntity insertAddressEntity) {
        if (insertAddressEntity != null) {
            String status = insertAddressEntity.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(InsertAddressActivity.this, "" + insertAddressEntity.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
