package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.callback.IGetItemIdCallback;
import com.changhao.weidu_project.entity.BannerEntity;
import com.changhao.weidu_project.entity.HomeEntity;
import com.changhao.weidu_project.ui.activity.RxxpActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private HomeEntity.ResultBean.RxxpBean rxxpBeans;
    private HomeEntity.ResultBean.PzshBean pzshBeans;
    private HomeEntity.ResultBean.MlssBean mlssBeans;
    private BannerEntity bannerEntity;
    private RxxpViewHolder rxxpViewHolder;
    private PzshViewHolder pzshViewHolder;
    private MlssViewHolder mlssViewHolder;
    private BannerViewHolder bannerViewHolder;

    public HomeAdapter(Context context) {
        rxxpBeans = new HomeEntity.ResultBean.RxxpBean();
        mlssBeans = new HomeEntity.ResultBean.MlssBean();
        pzshBeans = new HomeEntity.ResultBean.PzshBean();
        bannerEntity = new BannerEntity();
        this.context = context;
    }

    public void setResultBean(BannerEntity bannerEntity) {
        if (bannerEntity != null) {
            this.bannerEntity = bannerEntity;
        }
        notifyDataSetChanged();
    }

    public void setRxxpBeans(HomeEntity.ResultBean.RxxpBean rxxpBeans) {
        if (rxxpBeans != null) {
            this.rxxpBeans = rxxpBeans;
        }
        notifyDataSetChanged();
    }

    public void setPzshBeans(HomeEntity.ResultBean.PzshBean pzshBeans) {
        if (pzshBeans != null) {
            this.pzshBeans = pzshBeans;
        }
        notifyDataSetChanged();
    }

    public void setMlssBeans(HomeEntity.ResultBean.MlssBean mlssBeans) {
        if (mlssBeans != null) {
            this.mlssBeans = mlssBeans;
        }
        notifyDataSetChanged();
    }

    private final int BANNER_ITEM = 0;
    private final int RXXP_ITEM = 1;
    private final int PZSH_ITEM = 3;
    private final int MLSS_ITEM = 2;


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (getItemViewType(i) == RXXP_ITEM) {// 热销新品
            view = LayoutInflater.from(context).inflate(R.layout.item_rxxp, viewGroup, false);
            rxxpViewHolder = new RxxpViewHolder(view);
            return rxxpViewHolder;
        } else if (getItemViewType(i) == PZSH_ITEM) {// 品质生活
            view = LayoutInflater.from(context).inflate(R.layout.item_pzsh, viewGroup, false);
            pzshViewHolder = new PzshViewHolder(view);
            return pzshViewHolder;
        } else if (getItemViewType(i) == MLSS_ITEM) {// 魔力时尚
            view = LayoutInflater.from(context).inflate(R.layout.item_mlss, viewGroup, false);
            mlssViewHolder = new MlssViewHolder(view);
            return mlssViewHolder;
        } else if (getItemViewType(i) == BANNER_ITEM) {// 轮播
            view = LayoutInflater.from(context).inflate(R.layout.item_banner, viewGroup, false);
            bannerViewHolder = new BannerViewHolder(view);
            return bannerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final XRecyclerView.ViewHolder viewHolder, final int i) {
            if (getItemViewType(i) == RXXP_ITEM) {// 热销新品

                rxxpViewHolder.tv_rxxp_title.setText(rxxpBeans.getName());
                rxxpViewHolder.rxxp_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                RxxpAdapter rxxpAdapter = new RxxpAdapter(context);
                rxxpViewHolder.rxxp_rv.setAdapter(rxxpAdapter);
                rxxpViewHolder.tv_rxxp_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, RxxpActivity.class);
                        intent.putExtra("categoryId", rxxpBeans.getId());
                        context.startActivity(intent);

                    }
                });

                rxxpAdapter.setCommodityListBeans(rxxpBeans.getCommodityList());

            } else if (getItemViewType(i) == PZSH_ITEM) {// 品质生活
                pzshViewHolder.tv_pzsh_title.setText(pzshBeans.getName());
                pzshViewHolder.pzsh_rv.setLayoutManager(new GridLayoutManager(context, 2));
                PzshAdapter pzshAdapter = new PzshAdapter(context);
                pzshViewHolder.pzsh_rv.setAdapter(pzshAdapter);

                pzshAdapter.setCommodityListBeanXES(pzshBeans.getCommodityList());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (iGetItemIdCallback != null) {
                            iGetItemIdCallback.getItemId(pzshBeans.getCommodityList().get(i).getCommodityId() + "");
                        }
                    }
                });
            } else if (getItemViewType(i) == MLSS_ITEM) {// 魔力时尚
                mlssViewHolder.tv_mlss_title.setText(mlssBeans.getName());
                mlssViewHolder.mlss_rv.setLayoutManager(new LinearLayoutManager(context));
                MlssAdapter mlssAdapter = new MlssAdapter(context);
                mlssViewHolder.mlss_rv.setAdapter(mlssAdapter);

                mlssAdapter.setCommodityListBeanXXES(mlssBeans.getCommodityList());

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (iGetItemIdCallback != null) {
                            iGetItemIdCallback.getItemId(mlssBeans.getCommodityList().get(i).getCommodityId() + "");
                        }
                    }
                });
            } else if (getItemViewType(i) == BANNER_ITEM) {// 轮播
                final List<BannerEntity.ResultBean> result = bannerEntity.getResult();
                bannerViewHolder.xBanner.setData(result, null);
                bannerViewHolder.xBanner.setClipChildrenLeftRightMargin(20);
                bannerViewHolder.xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(context).load(result.get(i).getImageUrl()).into((ImageView) view);
                        bannerViewHolder.xBanner.setPageChangeDuration(1000);
                    }
                });
            }


    }

    public IGetItemIdCallback iGetItemIdCallback;

    public void setiGetItemIdCallback(IGetItemIdCallback iGetItemIdCallback) {
        this.iGetItemIdCallback = iGetItemIdCallback;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == BANNER_ITEM) {
            return BANNER_ITEM;
        } else if (position == RXXP_ITEM) {
            return RXXP_ITEM;
        } else if (position == PZSH_ITEM) {
            return PZSH_ITEM;
        } else if (position == MLSS_ITEM) {
            return MLSS_ITEM;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BannerViewHolder extends XRecyclerView.ViewHolder {

        private final XBanner xBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.xBanner);
        }
    }

    class RxxpViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_rxxp_title;
        private final RecyclerView rxxp_rv;
        private final TextView tv_rxxp_more;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rxxp_title = itemView.findViewById(R.id.tv_rxxp_title);
            rxxp_rv = itemView.findViewById(R.id.rxxp_rv);
            tv_rxxp_more = itemView.findViewById(R.id.tv_rxxp_more);
        }
    }

    class PzshViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_pzsh_title;
        private final RecyclerView pzsh_rv;
        private final TextView tv_pzsh_more;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pzsh_title = itemView.findViewById(R.id.tv_pzsh_title);
            pzsh_rv = itemView.findViewById(R.id.pzsh_rv);
            tv_pzsh_more = itemView.findViewById(R.id.tv_pzsh_more);
        }
    }

    class MlssViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_mlss_title;
        private final RecyclerView mlss_rv;
        private final TextView tv_mlss_more;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_mlss_title = itemView.findViewById(R.id.tv_mlss_title);
            mlss_rv = itemView.findViewById(R.id.mlss_rv);
            tv_mlss_more = itemView.findViewById(R.id.tv_mlss_more);
        }
    }
}
