package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.entity.BannerEntity;
import com.changhao.weidu_project.entity.HomeEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<HomeEntity.ResultBean.RxxpBean> rxxpBeans;
    private List<HomeEntity.ResultBean.PzshBean> pzshBeans;
    private List<HomeEntity.ResultBean.MlssBean> mlssBeans;
    private BannerEntity bannerEntity;
    private RxxpViewHolder rxxpViewHolder;
    private PzshViewHolder pzshViewHolder;
    private MlssViewHolder mlssViewHolder;
    private BannerViewHolder bannerViewHolder;

    public HomeAdapter(Context context) {
        rxxpBeans = new ArrayList<>();
        pzshBeans = new ArrayList<>();
        mlssBeans = new ArrayList<>();
        bannerEntity = new BannerEntity();
        this.context = context;
    }

    public void setResultBean(BannerEntity bannerEntity) {
        if (bannerEntity != null) {
            this.bannerEntity = bannerEntity;
        }
        notifyDataSetChanged();
    }

    public void setRxxpBeans(List<HomeEntity.ResultBean.RxxpBean> rxxpBeans) {
        if (rxxpBeans != null) {
            this.rxxpBeans = rxxpBeans;
        }
        notifyDataSetChanged();
    }

    public void setPzshBeans(List<HomeEntity.ResultBean.PzshBean> pzshBeans) {
        if (pzshBeans != null) {
            this.pzshBeans = pzshBeans;
        }
        notifyDataSetChanged();
    }

    public void setMlssBeans(List<HomeEntity.ResultBean.MlssBean> mlssBeans) {
        if (mlssBeans != null) {
            this.mlssBeans = mlssBeans;
        }
        notifyDataSetChanged();
    }

    private final int BANNER_ITEM = 0;
    private final int RXXP_ITEM = 1;
    private final int PZSH_ITEM = 2;
    private final int MLSS_ITEM = 3;


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (getItemViewType(i) == RXXP_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_rxxp, viewGroup, false);
            rxxpViewHolder = new RxxpViewHolder(view);
            return rxxpViewHolder;
        } else if (getItemViewType(i) == PZSH_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_pzsh, viewGroup, false);
            pzshViewHolder = new PzshViewHolder(view);
            return pzshViewHolder;
        } else if (getItemViewType(i) == MLSS_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_mlss, viewGroup, false);
            mlssViewHolder = new MlssViewHolder(view);
            return mlssViewHolder;
        } else if (getItemViewType(i) == BANNER_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_banner, viewGroup, false);
            bannerViewHolder = new BannerViewHolder(view);
            return bannerViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final XRecyclerView.ViewHolder viewHolder, final int i) {
        if (rxxpBeans.size() > 0 && pzshBeans.size() > 0 && mlssBeans.size() > 0) {
            if (getItemViewType(i) == RXXP_ITEM) {
                HomeEntity.ResultBean.RxxpBean rxxpBean = rxxpBeans.get(0);
                rxxpViewHolder.tv_rxxp_title.setText(rxxpBean.getName());
                rxxpViewHolder.rxxp_xrv.setLayoutManager(new LinearLayoutManager(context));
                RxxpAdapter rxxpAdapter = new RxxpAdapter(context);
                rxxpViewHolder.rxxp_xrv.setAdapter(rxxpAdapter);
                rxxpViewHolder.rxxp_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        rxxpViewHolder.rxxp_xrv.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        rxxpViewHolder.rxxp_xrv.loadMoreComplete();
                    }
                });
                rxxpAdapter.setCommodityListBeans(rxxpBean.getCommodityList());

            } else if (getItemViewType(i) == PZSH_ITEM) {
                HomeEntity.ResultBean.PzshBean pzshBean = pzshBeans.get(0);
                pzshViewHolder.tv_pzsh_title.setText(pzshBean.getName());
                pzshViewHolder.pzsh_xrv.setLayoutManager(new LinearLayoutManager(context));
                PzshAdapter pzshAdapter = new PzshAdapter(context);
                pzshViewHolder.pzsh_xrv.setAdapter(pzshAdapter);
                pzshViewHolder.pzsh_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        pzshViewHolder.pzsh_xrv.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        pzshViewHolder.pzsh_xrv.loadMoreComplete();
                    }
                });
                pzshAdapter.setCommodityListBeanXES(pzshBean.getCommodityList());
            } else if (getItemViewType(i) == MLSS_ITEM) {
                HomeEntity.ResultBean.MlssBean mlssBean = mlssBeans.get(0);
                mlssViewHolder.tv_mlss_title.setText(mlssBean.getName());
                mlssViewHolder.mlss_xrv.setLayoutManager(new LinearLayoutManager(context));
                MlssAdapter mlssAdapter = new MlssAdapter(context);
                mlssViewHolder.mlss_xrv.setAdapter(mlssAdapter);
                mlssViewHolder.mlss_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        mlssViewHolder.mlss_xrv.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        mlssViewHolder.mlss_xrv.loadMoreComplete();
                    }
                });
                mlssAdapter.setCommodityListBeanXXES(mlssBean.getCommodityList());
            } else if (getItemViewType(i) == BANNER_ITEM) {
                final List<BannerEntity.ResultBean> result = bannerEntity.getResult();
                bannerViewHolder.xBanner.setData(result, null);
                bannerViewHolder.xBanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
                        Glide.with(context).load(result.get(i).getImageUrl()).into((ImageView) view);
                        bannerViewHolder.xBanner.setPageChangeDuration(1000);
                    }
                });
            }
        }
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
        private final XRecyclerView rxxp_xrv;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rxxp_title = itemView.findViewById(R.id.tv_rxxp_title);
            rxxp_xrv = itemView.findViewById(R.id.rxxp_xrv);
        }
    }

    class PzshViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_pzsh_title;
        private final XRecyclerView pzsh_xrv;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_pzsh_title = itemView.findViewById(R.id.tv_pzsh_title);
            pzsh_xrv = itemView.findViewById(R.id.pzsh_xrv);
        }
    }

    class MlssViewHolder extends XRecyclerView.ViewHolder {

        private final TextView tv_mlss_title;
        private final XRecyclerView mlss_xrv;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_mlss_title = itemView.findViewById(R.id.tv_mlss_title);
            mlss_xrv = itemView.findViewById(R.id.mlss_xrv);
        }
    }
}
