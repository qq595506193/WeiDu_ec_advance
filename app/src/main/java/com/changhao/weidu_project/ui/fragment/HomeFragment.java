package com.changhao.weidu_project.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.HomeAdapter;
import com.changhao.weidu_project.contract.IBannerContract;
import com.changhao.weidu_project.contract.IHomeContract;
import com.changhao.weidu_project.entity.BannerEntity;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.presenter.BannerPresenter;
import com.changhao.weidu_project.presenter.HomePresenter;
import com.changhao.weidu_project.ui.activity.ProductDetailsActivity;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.changhao.weidu_project.utils.RetrofitUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment implements IHomeContract.IHomeView, IBannerContract.IBannerView {


    @BindView(R.id.imageBtn_menu)
    ImageButton imageBtn_menu;
    @BindView(R.id.imageBtn_search)
    ImageButton imageBtn_search;
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
        if (RetrofitUtils.getInstance().isNetWorkConnected(getActivity()) == false) {
            Toast.makeText(getActivity(), "没网", Toast.LENGTH_SHORT).show();

        } else {
            HashMap<String, String> homeParams = new HashMap<>();
            homePresenter.getHome(homeParams);
            HashMap<String, String> bannerParams = new HashMap<>();
            bannerPresenter.getBanner(bannerParams);
        }

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
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
    protected String getCacheDir() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void itemClick(String itemId) {
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("itemId", itemId);
        startActivity(intent);
    }

    @Override
    public void onRxxpHomeSuccess(HomeEntity.ResultBean.RxxpBean rxxpBeans) {
        if (rxxpBeans != null) {
            homeAdapter.setRxxpBeans(rxxpBeans);

        }
    }

    @Override
    public void onMlssHomeSuccess(HomeEntity.ResultBean.MlssBean mlssBeans) {
        if (mlssBeans != null) {
            homeAdapter.setMlssBeans(mlssBeans);
        }
    }

    @Override
    public void onPzshHomeSuccess(HomeEntity.ResultBean.PzshBean pzshBeans) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
