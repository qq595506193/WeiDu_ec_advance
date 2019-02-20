package com.changhao.weidu_project.callback;

public interface IRequestCallback {
    void onSuccess(String result);

    void onFailed(String msg);
}
