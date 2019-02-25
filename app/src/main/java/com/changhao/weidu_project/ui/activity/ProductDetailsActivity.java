package com.changhao.weidu_project.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.adapter.ProductDetailsAdapter;
import com.changhao.weidu_project.contract.IProductDetailsContract;
import com.changhao.weidu_project.contract.ISyncShoppingCartContract;
import com.changhao.weidu_project.entity.ProductDetailsEntity;
import com.changhao.weidu_project.entity.SyncShoppingCartEntity;
import com.changhao.weidu_project.presenter.ProductDetailsPresenter;
import com.changhao.weidu_project.presenter.SyncShoppingCartPresenter;
import com.changhao.weidu_project.ui.base.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetailsActivity extends BaseActivity implements IProductDetailsContract.IProductDetailsView, ISyncShoppingCartContract.ISyncShoppingCartView {

    @BindView(R.id.par_image_back)
    ImageButton tv_left_back;
    @BindView(R.id.par_goods)
    TextView par_goods;
    @BindView(R.id.par_par)
    TextView par_par;
    @BindView(R.id.par_comment)
    TextView par_comment;
    @BindView(R.id.par_xbanner)
    XBanner par_xbanner;
    @BindView(R.id.par_text_price)
    TextView par_text_price;
    @BindView(R.id.par_text_num)
    TextView par_text_num;
    @BindView(R.id.par_text_name)
    TextView par_text_name;
    @BindView(R.id.par_text_content)
    TextView par_text_content;
    @BindView(R.id.par_text_kg)
    TextView par_text_kg;
    @BindView(R.id.par_webview)
    WebView par_webview;
    @BindView(R.id.par_image_addshop)
    ImageButton par_image_addshop;
    @BindView(R.id.par_image_buy)
    ImageButton par_image_buy;


    private ProductDetailsPresenter productDetailsPresenter;
    private ProductDetailsAdapter productDetailsAdapter;
    private String itemId;
    List<String> list = new ArrayList<>();
    private SyncShoppingCartPresenter syncShoppingCartPresenter;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("commodityId", itemId);
        productDetailsPresenter.getProductDetails(params);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        productDetailsPresenter = new ProductDetailsPresenter(this);
        syncShoppingCartPresenter = new SyncShoppingCartPresenter(this);


        tv_left_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected int getViewId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void onSuccess(ProductDetailsEntity productResultBeans) {
        if (productResultBeans != null) {
            /*productDetailsAdapter.setProductDetailsEntity(productResultBeans);*/
            final ProductDetailsEntity.ResultBean result = productResultBeans.getResult();
            final String[] split = result.getPicture().split(",");
            for (String s : split) {
                list.add(s);
            }
            par_xbanner.setData(list, null);
            par_xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(ProductDetailsActivity.this).load(list.get(position)).into((ImageView) view);
                    par_xbanner.setPageChangeDuration(1000);
                }
            });

            par_text_price.setText("￥：" + result.getPrice());
            par_text_num.setText(result.getSaleNum() + "");
            par_text_name.setText(result.getCommodityName());
            par_text_content.setText(result.getDescribe());
            par_text_kg.setText(result.getWeight() + "kg");

            par_webview.loadData(result.getDetails(), "text/html", "utf-8");

            par_image_addshop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ProductDetailsActivity.this, "点击了加购", Toast.LENGTH_SHORT).show();
                    HashMap<String, String> params = new HashMap<>();
                    SharedPreferences tiao = getSharedPreferences("tiao", MODE_PRIVATE);
                    String sessionId = tiao.getString("sessionId", null);
                    String userId = tiao.getString("userId", null);

                    params.put("sessionId", sessionId);
                    params.put("userId", userId);
                    params.put("data", result.getCommodityId() + "");
                    syncShoppingCartPresenter.getSyncShopping(params);
                }
            });

            par_image_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


        }
    }

    @Override
    public void onSuccess(SyncShoppingCartEntity syncShoppingCartEntity) {
        if (syncShoppingCartEntity != null) {
            String status = syncShoppingCartEntity.getStatus();
            if (status.equals("0000")) {
                Toast.makeText(ProductDetailsActivity.this, syncShoppingCartEntity.getMessage()+"!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(ProductDetailsActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }
}
