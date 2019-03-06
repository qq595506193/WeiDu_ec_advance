package com.changhao.weidu_project.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.SearchShoppingAdapter;
import com.changhao.weidu_project.callback.NotifyNum;
import com.changhao.weidu_project.entity.SearchShoppingEntity;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements NotifyNum {

    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.xrv_git_order)
    XRecyclerView xrv_git_order;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_commit_order)
    TextView tv_commit_order;
    private SearchShoppingAdapter searchShoppingAdapter;
    private List<SearchShoppingEntity.ResultBean> resultBean;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        searchShoppingAdapter = new SearchShoppingAdapter(OrderActivity.this, this);
        xrv_git_order.setLayoutManager(new LinearLayoutManager(this));
        xrv_git_order.setAdapter(searchShoppingAdapter);
        searchShoppingAdapter.setResultBeans(resultBean);

        xrv_git_order.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrv_git_order.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrv_git_order.loadMoreComplete();
            }
        });

        zPrice();


        tv_commit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_order;
    }

    @Subscribe(sticky = true)
    public void gitOrder(List<SearchShoppingEntity.ResultBean> resultBeans) {

        resultBean = resultBeans;

    }

    @Override
    public void notifynum() {
        zPrice();
    }

    private void zPrice() {
        //double total = 0;
        for (SearchShoppingEntity.ResultBean bean : resultBean) {
            if (bean.isCheck()) {
                tv_price.setText("共" + bean.getCount() + "商品，需要支付" + bean.getPrice() * bean.getNum() + "元");
            } else {
                tv_price.setText("0.0");
            }
        }


    }

    @Override
    public void isCheck(boolean check) {

    }
}
