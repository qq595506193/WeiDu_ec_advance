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

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder> {
    private Context context;
    private List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> commodityListBeans;

    public RxxpAdapter(Context context) {
        commodityListBeans = new ArrayList<>();
        this.context = context;
    }

    public void setCommodityListBeans(List<HomeEntity.ResultBean.RxxpBean.CommodityListBean> commodityListBeans) {
        if (commodityListBeans != null) {
            this.commodityListBeans = commodityListBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RxxpAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rxxp_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpAdapter.ViewHolder viewHolder, int i) {
        final HomeEntity.ResultBean.RxxpBean.CommodityListBean commodityListBean = commodityListBeans.get(i);
        Uri uri = Uri.parse(commodityListBean.getMasterPic());
        viewHolder.iv_rxxp_icon.setImageURI(uri);
        viewHolder.tv_rxxp_name.setText(commodityListBean.getCommodityName());
        viewHolder.tv_rxxp_price.setText("￥：" + commodityListBean.getPrice());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(commodityListBean.getCommodityId() + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return commodityListBeans == null ? 0 : commodityListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView iv_rxxp_icon;
        private final TextView tv_rxxp_name;
        private final TextView tv_rxxp_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_rxxp_icon = itemView.findViewById(R.id.iv_rxxp_icon);
            tv_rxxp_name = itemView.findViewById(R.id.tv_rxxp_name);
            tv_rxxp_price = itemView.findViewById(R.id.tv_rxxp_price);
        }
    }
}
