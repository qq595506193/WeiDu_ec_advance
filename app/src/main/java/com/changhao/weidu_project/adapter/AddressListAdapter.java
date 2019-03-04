package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.entity.AddressListEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddressListAdapter extends XRecyclerView.Adapter<AddressListAdapter.ViewHolder> {
    private Context context;
    private List<AddressListEntity.Result> results;

    public AddressListAdapter(Context context) {
        results = new ArrayList<>();
        this.context = context;
    }

    public void setResults(List<AddressListEntity.Result> results) {
        if (results != null) {
            this.results = results;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListAdapter.ViewHolder viewHolder, int i) {
        final AddressListEntity.Result result = results.get(i);
        viewHolder.item_address_name.setText(result.realName);
        viewHolder.item_address_phone.setText(result.phone + "");
        viewHolder.item_address_content.setText(result.address);
        viewHolder.item_ck_moren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    result.whetherDefault = 1;
                } else {
                    result.whetherDefault = 0;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_address_name;
        private final TextView item_address_phone;
        private final TextView item_address_content;
        private final CheckBox item_ck_moren;
        private final Button item_btn_update;
        private final Button item_btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_address_name = itemView.findViewById(R.id.item_address_name);
            item_address_phone = itemView.findViewById(R.id.item_address_phone);
            item_address_content = itemView.findViewById(R.id.item_address_content);
            item_ck_moren = itemView.findViewById(R.id.item_ck_moren);
            item_btn_update = itemView.findViewById(R.id.item_btn_update);
            item_btn_delete = itemView.findViewById(R.id.item_btn_delete);
        }
    }
}
