package com.changhao.weidu_project.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_project.R;

public class AddMinusView extends LinearLayout {

    private TextView tv_subtract, tv_plus;
    private TextView ed_minus;
    private int num = 1;

    public AddMinusView(Context context) {
        this(context, null);
    }

    public AddMinusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        View view = LayoutInflater.from(context).inflate(R.layout.add_minus_layout, this, true);

        tv_subtract = view.findViewById(R.id.tv_subtract);
        tv_plus = view.findViewById(R.id.tv_plus);
        ed_minus = view.findViewById(R.id.tv_minus);
        ed_minus.setText("1");

        tv_plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                ed_minus.setText(num + "");
                if (addMinusCallback != null) {
                    addMinusCallback.numCallback(num);
                }
            }
        });

        tv_subtract.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num == 0) {
                    num = 1;
                    Toast.makeText(getContext(), "不能再减了！", Toast.LENGTH_SHORT).show();
                }
                ed_minus.setText(num + "");

                if (addMinusCallback != null) {
                    addMinusCallback.numCallback(num);
                }
            }
        });

    }

    public void setNum(int num) {
        ed_minus.setText(num + "");
    }

    public int getNum() {
        return num;
    }

    private AddMinusCallback addMinusCallback;

    public void setAddMinusCallback(AddMinusCallback addMinusCallback) {
        this.addMinusCallback = addMinusCallback;
    }

    public interface AddMinusCallback {
        void numCallback(int num);
    }

}
