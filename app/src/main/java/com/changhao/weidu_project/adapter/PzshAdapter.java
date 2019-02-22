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

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.ViewHolder> {
    private Context context;
    private List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXES;

    public PzshAdapter(Context context) {
        commodityListBeanXES = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeanXES(List<HomeEntity.ResultBean.PzshBean.CommodityListBeanX> commodityListBeanXES) {
        if (commodityListBeanXES != null) {
            this.commodityListBeanXES = commodityListBeanXES;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PzshAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pzsh_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PzshAdapter.ViewHolder viewHolder, int i) {
        final HomeEntity.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = commodityListBeanXES.get(i);
        Uri uri = Uri.parse(commodityListBeanX.getMasterPic());
        viewHolder.iv_pzsh_icon.setImageURI(uri);
        viewHolder.tv_pzsh_name.setText(commodityListBeanX.getCommodityName());
        viewHolder.tv_pzsh_price.setText(commodityListBeanX.getPrice());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(commodityListBeanX.getCommodityId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityListBeanXES == null ? 0 : commodityListBeanXES.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv_pzsh_icon;
        private final TextView tv_pzsh_name;
        private final TextView tv_pzsh_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_pzsh_icon = itemView.findViewById(R.id.iv_pzsh_icon);
            tv_pzsh_name = itemView.findViewById(R.id.tv_pzsh_name);
            tv_pzsh_price = itemView.findViewById(R.id.tv_pzsh_price);
        }
    }
}
