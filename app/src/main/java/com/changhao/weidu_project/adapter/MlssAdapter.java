package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.entity.HomeEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MlssAdapter extends XRecyclerView.Adapter<MlssAdapter.ViewHolder> {
    private Context context;
    private List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES;

    public MlssAdapter(Context context) {
        commodityListBeanXXES = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeanXXES(List<HomeEntity.ResultBean.MlssBean.CommodityListBeanXX> commodityListBeanXXES) {
        if (commodityListBeanXXES != null) {
            this.commodityListBeanXXES = commodityListBeanXXES;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MlssAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mlss_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MlssAdapter.ViewHolder viewHolder, int i) {
        HomeEntity.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = commodityListBeanXXES.get(i);
        Uri uri = Uri.parse(commodityListBeanXX.getMasterPic());
        viewHolder.iv_mlss_icon.setImageURI(uri);
        viewHolder.tv_mlss_name.setText(commodityListBeanXX.getCommodityName());
        viewHolder.tv_mlss_price.setText(commodityListBeanXX.getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityListBeanXXES == null ? 0 : commodityListBeanXXES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv_mlss_icon;
        private final TextView tv_mlss_name;
        private final TextView tv_mlss_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_mlss_icon = itemView.findViewById(R.id.iv_mlss_icon);
            tv_mlss_name = itemView.findViewById(R.id.tv_mlss_name);
            tv_mlss_price = itemView.findViewById(R.id.tv_mlss_price);
        }
    }
}
