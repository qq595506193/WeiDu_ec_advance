package com.changhao.weidu_project.callback;

public interface IOkHttpCallback {
    void onSuccess(String result);

    void onFailed(String msg);
}
