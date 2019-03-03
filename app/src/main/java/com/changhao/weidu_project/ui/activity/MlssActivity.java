package com.changhao.weidu_project.ui.activity;

import android.support.v7.widget.GridLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.MlssAdapter;
import com.changhao.weidu_project.contract.IMlssContract;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.presenter.MlssPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MlssActivity extends BaseActivity implements IMlssContract.IMlssView {

    @BindView(R.id.seek_navigation)
    TextView seek_navigation;

    @BindView(R.id.seek_search_edit)
    EditText seek_search_edit;

    @BindView(R.id.seek_search)
    TextView seek_search;

    @BindView(R.id.seek_claasify_xrv)
    XRecyclerView seek_claasify_xrv;
    private MlssAdapter mlssAdapter;
    private MlssPresenter mlssPresenter;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        mlssPresenter.getMlss(params);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mlssPresenter = new MlssPresenter(this);
        mlssAdapter = new MlssAdapter(this);
        seek_claasify_xrv.setLayoutManager(new GridLayoutManager(this, 2));
        seek_claasify_xrv.setAdapter(mlssAdapter);
        seek_claasify_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
                seek_claasify_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                seek_claasify_xrv.loadMoreComplete();
            }
        });

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_mlss;
    }


    @Override
    public void onSuccess(List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES) {
        if (commodityListBeanXXES != null) {
            mlssAdapter.setCommodityListBeanXXES(commodityListBeanXXES);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
