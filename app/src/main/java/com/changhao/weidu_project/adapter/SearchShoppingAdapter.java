package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.callback.NotifyNum;
import com.changhao.weidu_project.entity.SearchShoppingEntity;
import com.changhao.weidu_project.widget.AddMinusView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchShoppingAdapter extends XRecyclerView.Adapter<SearchShoppingAdapter.ViewHolder> {
    private Context context;
    private List<SearchShoppingEntity.ResultBean> resultBeans;
    private NotifyNum notifyNum;

    public SearchShoppingAdapter(Context context, NotifyNum notifyNum) {
        resultBeans = new ArrayList<>();
        this.context = context;
        this.notifyNum = notifyNum;
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
    public void onBindViewHolder(@NonNull final SearchShoppingAdapter.ViewHolder viewHolder, int i) {
        final SearchShoppingEntity.ResultBean resultBean = resultBeans.get(i);
        Glide.with(context).load(resultBean.getPic()).into(viewHolder.item_select_image);
        viewHolder.item_select_text_name.setText(resultBean.getCommodityName());
        viewHolder.item_select_text_price.setText("￥：" + resultBean.getPrice());
        viewHolder.item_select_box_all.setChecked(resultBean.isCheck());
        // 点击Checked
        viewHolder.item_select_box_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultBean.setCheck(viewHolder.item_select_box_all.isChecked());
                notifyDataSetChanged();
                boolean check = true;
                for (SearchShoppingEntity.ResultBean bean : resultBeans) {
                    if (!bean.isCheck()) {
                        check = false;
                    }
                    if (notifyNum != null) {
                        notifyNum.isCheck(check);
                    }
                }
                if (notifyNum != null) {
                    notifyNum.notifynum();
                }
            }
        });

        viewHolder.addMinusView.setAddMinusCallback(new AddMinusView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                resultBean.setNum(num);
                if (notifyNum != null) {
                    notifyNum.notifynum();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return resultBeans == null ? 0 : resultBeans.size();
    }

    public class ViewHolder extends XRecyclerView.ViewHolder {

        private final TextView item_select_text_delete;
        private final CheckBox item_select_box_all;
        private final ImageView item_select_image;
        private final TextView item_select_text_name;
        private final TextView item_select_text_price;
        private final AddMinusView addMinusView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_select_text_delete = itemView.findViewById(R.id.item_select_text_delete);
            item_select_box_all = itemView.findViewById(R.id.item_select_box_all);
            item_select_image = itemView.findViewById(R.id.item_select_image);
            item_select_text_name = itemView.findViewById(R.id.item_select_text_name);
            item_select_text_price = itemView.findViewById(R.id.item_select_text_price);
            addMinusView = itemView.findViewById(R.id.add_minus);
        }
    }
}
