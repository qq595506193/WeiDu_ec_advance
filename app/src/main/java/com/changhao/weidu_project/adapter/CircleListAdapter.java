package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.ILikeContract;
import com.changhao.weidu_project.entity.CircleListEntity;
import com.changhao.weidu_project.entity.LikeEntity;
import com.changhao.weidu_project.presenter.LikePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CircleListAdapter extends XRecyclerView.Adapter<CircleListAdapter.ViewHolder> implements ILikeContract.ILikeView {
    private Context context;
    private List<CircleListEntity.ResultBean> circleResultBeans;
    private ViewHolder viewHolder;

    public CircleListAdapter(Context context) {
        circleResultBeans = new ArrayList<>();
        this.context = context;
    }

    public void setCircleResultBeans(List<CircleListEntity.ResultBean> circleResultBeans) {
        if (circleResultBeans != null) {
            this.circleResultBeans = circleResultBeans;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CircleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_circle_list, viewGroup, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public static String getDateStr(Date date, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleListAdapter.ViewHolder viewHolder, int i) {
        CircleListEntity.ResultBean resultBean = circleResultBeans.get(i);
        Uri uri = Uri.parse(resultBean.getHeadPic());
        viewHolder.circle_head_icon.setImageURI(uri);
        viewHolder.tv_head_name.setText(resultBean.getNickName());

        Date date = new Date(resultBean.getCreateTime());
        viewHolder.tv_head_time.setText(getDateStr(date,null));

        viewHolder.tv_content.setText(resultBean.getContent());
        viewHolder.tv_content_praise_count.setText(resultBean.getGreatNum() + "");

        List<String> images = new ArrayList<>();

        String image = resultBean.getImage();
        if (image.equals("")) {
            viewHolder.circle_rv_image.setVisibility(View.GONE);
        } else {
            viewHolder.circle_rv_image.setVisibility(View.VISIBLE);
        }
        String[] split = image.split(",");

        images.clear();

        for (String s : split) {
            images.add(s);
        }

        CircleImageAdapter circleImageAdapter = new CircleImageAdapter(context);
        viewHolder.circle_rv_image.setAdapter(circleImageAdapter);
        circleImageAdapter.setImages(images);

        if (split.length == 1) {
            viewHolder.circle_rv_image.setLayoutManager(new GridLayoutManager(context, 1));
        } else if (split.length == 2) {
            viewHolder.circle_rv_image.setLayoutManager(new GridLayoutManager(context, 2));
        } else if (split.length == 3) {
            viewHolder.circle_rv_image.setLayoutManager(new GridLayoutManager(context, 3));
        } else {
            viewHolder.circle_rv_image.setLayoutManager(new GridLayoutManager(context, 3));
        }

        int id = resultBean.getId();// 每条圈子Id
        int greatNum = resultBean.getGreatNum();

        LikePresenter likePresenter = new LikePresenter(this);
        initPraise(id, greatNum, likePresenter);

    }

    private void initPraise(final int i, final int id, final LikePresenter likePresenter) {
        viewHolder.tv_content_praise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> bodyParams = new HashMap<>();
                bodyParams.put("circleId", id + "");
                HashMap<String, String> headersParams = new HashMap<>();
                headersParams.put("userId", "831");
                headersParams.put("sessionId", "1550730837662831");

                likePresenter.getLike(bodyParams);
                likePresenter.getLike(headersParams);
                viewHolder.tv_content_praise_count.setText(i + 1 + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return circleResultBeans == null ? 0 : circleResultBeans.size();
    }

    @Override
    public void onSuccess(LikeEntity likeEntity) {
        if (likeEntity != null) {
            if (likeEntity.equals("0000")) {
                Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailed(String msg) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView circle_head_icon;
        private final TextView tv_head_name;
        private final TextView tv_head_time;
        private final TextView tv_content;
        private final RecyclerView circle_rv_image;
        private final TextView tv_content_praise_count;
        private final TextView tv_content_praise;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_head_icon = itemView.findViewById(R.id.circle_head_icon);
            tv_head_name = itemView.findViewById(R.id.tv_head_name);
            tv_head_time = itemView.findViewById(R.id.tv_head_time);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_content_praise_count = itemView.findViewById(R.id.tv_content_praise_count);
            tv_content_praise = itemView.findViewById(R.id.tv_content_praise);
            circle_rv_image = itemView.findViewById(R.id.circle_rv_image);
        }
    }
}
