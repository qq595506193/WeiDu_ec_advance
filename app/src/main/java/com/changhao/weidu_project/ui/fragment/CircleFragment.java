package com.changhao.weidu_project.ui.fragment;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.CircleListAdapter;
import com.changhao.weidu_project.contract.ICircleListContract;
import com.changhao.weidu_project.entity.CircleListEntity;
import com.changhao.weidu_project.presenter.CircleListPresenter;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleFragment extends BaseFragment implements ICircleListContract.ICircleListView {
    @BindView(R.id.circle_xrv)
    XRecyclerView circle_xrv;
    private CircleListPresenter circleListPresenter;
    private CircleListAdapter circleListAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", "831");
        params.put("sessionId", "1550730837662831");
        circleListPresenter.getCircleList(params);
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        circleListPresenter = new CircleListPresenter(this);
        circle_xrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        circleListAdapter = new CircleListAdapter(getActivity());
        circle_xrv.setAdapter(circleListAdapter);
        circle_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
                circle_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                circle_xrv.loadMoreComplete();
            }
        });
    }

    @Override
    public void onSuccess(List<CircleListEntity.ResultBean> circleResultBeans) {
        if (circleResultBeans != null) {
            circleListAdapter.setCircleResultBeans(circleResultBeans);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
