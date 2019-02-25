package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.entity.SearchShoppingEntity;

import java.util.ArrayList;
import java.util.List;

public class SearchShoppingAdapter extends RecyclerView.Adapter<SearchShoppingAdapter.ViewHolder> {
    private Context context;
    private List<SearchShoppingEntity.ResultBean> resultBeans;

    public SearchShoppingAdapter(Context context) {
        resultBeans = new ArrayList<>();
        this.context = context;
    }

    public void setResultBeans(List<SearchShoppingEntity.ResultBean> resultBeans) {
        if (resultBeans != null) {
            this.resultBeans = resultBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchShoppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_shopping, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchShoppingAdapter.ViewHolder viewHolder, int i) {
        SearchShoppingEntity.ResultBean resultBean = resultBeans.get(i);
        Glide.with(context).load(resultBean.getPic()).into(viewHolder.item_select_image);
        viewHolder.item_select_text_name.setText(resultBean.getCommodityName());
        viewHolder.item_select_text_price.setText("￥：" + resultBean.getPrice());
        // 点击全选
        viewHolder.item_select_box_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 点击删除
        viewHolder.item_select_text_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultBeans == null ? 0 : resultBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_select_text_delete;
        private final CheckBox item_select_box_all;
        private final ImageView item_select_image;
        private final TextView item_select_text_name;
        private final TextView item_select_text_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_select_text_delete = itemView.findViewById(R.id.item_select_text_delete);
            item_select_box_all = itemView.findViewById(R.id.item_select_box_all);
            item_select_image = itemView.findViewById(R.id.item_select_image);
            item_select_text_name = itemView.findViewById(R.id.item_select_text_name);
            item_select_text_price = itemView.findViewById(R.id.item_select_text_price);

        }
    }
}
