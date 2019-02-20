package com.changhao.weidu_project.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.HomeAdapter;
import com.changhao.weidu_project.contract.IBannerContract;
import com.changhao.weidu_project.contract.IHomeContract;
import com.changhao.weidu_project.entity.BannerEntity;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.presenter.BannerPresenter;
import com.changhao.weidu_project.presenter.HomePresenter;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements IHomeContract.IHomeView, IBannerContract.IBannerView {

    @BindView(R.id.xBanner)
    XBanner xBanner;
    @BindView(R.id.xrv_home)
    XRecyclerView xrv_home;
    private HomePresenter homePresenter;
    private HomeAdapter homeAdapter;
    private BannerPresenter bannerPresenter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        HashMap<String, String> homeParams = new HashMap<>();
        homePresenter.getHome(homeParams);
        HashMap<String, String> bannerParams = new HashMap<>();
        bannerPresenter.getBanner(bannerParams);

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        homePresenter = new HomePresenter(this);
        bannerPresenter = new BannerPresenter(this);
        xrv_home.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter(getActivity());
        xrv_home.setAdapter(homeAdapter);
        xrv_home.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initData();
                xrv_home.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrv_home.loadMoreComplete();
            }
        });

    }

    @Override
    public void onRxxpHomeSuccess(List<HomeEntity.ResultBean.RxxpBean> rxxpBeans) {
        if (rxxpBeans != null) {
            homeAdapter.setRxxpBeans(rxxpBeans);
        }
    }

    @Override
    public void onMlssHomeSuccess(List<HomeEntity.ResultBean.MlssBean> mlssBeans) {
        if (mlssBeans != null) {
            homeAdapter.setMlssBeans(mlssBeans);
        }
    }

    @Override
    public void onPzshHomeSuccess(List<HomeEntity.ResultBean.PzshBean> pzshBeans) {
        if (pzshBeans != null) {
            homeAdapter.setPzshBeans(pzshBeans);
        }
    }

    @Override
    public void onSuccess(BannerEntity bannerEntity) {
        if (bannerEntity != null) {
            homeAdapter.setResultBean(bannerEntity);
        }
    }

    @Override
    public void onFailed(String msg) {

    }
}
