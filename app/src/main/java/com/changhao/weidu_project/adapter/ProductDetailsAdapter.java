package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.changhao.weidu_project.R;
import com.changhao.weidu_project.entity.ProductDetailsEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private ProductDetailsEntity productDetailsEntity;

    List<String> list = new ArrayList<>();

    private final int GOODS_ITEM = 0;
    private final int DETAILS_ITEM = 1;
    private final int COMMENT_ITEM = 2;
    private GoodsViewHolder goodsViewHolder;
    private DetailsViewHolder detailsViewHolder;


    public ProductDetailsAdapter(Context context) {
        productDetailsEntity = new ProductDetailsEntity();
        this.context = context;
    }

    public void setProductDetailsEntity(ProductDetailsEntity productDetailsEntity) {
        if (productDetailsEntity != null) {
            this.productDetailsEntity = productDetailsEntity;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        if (getItemViewType(i) == GOODS_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_goods, viewGroup, false);
            goodsViewHolder = new GoodsViewHolder(view);
            return goodsViewHolder;
        } else if (getItemViewType(i) == DETAILS_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.item_details, viewGroup, false);
            detailsViewHolder = new DetailsViewHolder(view);
            return detailsViewHolder;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_comment, viewGroup, false);
            CommentViewHolder commentViewHolder = new CommentViewHolder(view);
            return commentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == GOODS_ITEM) {
            final ProductDetailsEntity.ResultBean result = productDetailsEntity.getResult();
            if (result == null) {
                return;
            }
            final String[] split = result.getPicture().split(",");
            for (String s : split) {
                list.add(s);
            }
            goodsViewHolder.xbanner_icon_goods.setData(list, null);
            goodsViewHolder.xbanner_icon_goods.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(list.get(position)).into((ImageView) view);
                    goodsViewHolder.xbanner_icon_goods.setPageChangeDuration(1000);
                }
            });
            goodsViewHolder.tv_goods_price.setText(result.getPrice() + "");
            goodsViewHolder.tv_goods_content.setText(result.getCommodityName());
        } else if (getItemViewType(i) == DETAILS_ITEM) {
            ProductDetailsEntity.ResultBean result = productDetailsEntity.getResult();
            if (result == null) {
                return;
            }
            detailsViewHolder.web_view.loadData(result.getDetails(), "text/html", "utf-8");
        } else {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == GOODS_ITEM) {
            return GOODS_ITEM;
        } else if (position == DETAILS_ITEM) {
            return DETAILS_ITEM;
        } else {
            return COMMENT_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class GoodsViewHolder extends XRecyclerView.ViewHolder {

        private final XBanner xbanner_icon_goods;
        private final TextView tv_goods_price;
        private final TextView tv_goods_content;

        public GoodsViewHolder(@NonNull View itemView) {
            super(itemView);
            xbanner_icon_goods = itemView.findViewById(R.id.xbanner_icon_goods);
            tv_goods_price = itemView.findViewById(R.id.tv_goods_price);
            tv_goods_content = itemView.findViewById(R.id.tv_goods_content);
        }
    }

    class DetailsViewHolder extends XRecyclerView.ViewHolder {

        private final WebView web_view;
        private final TextView tv_details;

        public DetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_details = itemView.findViewById(R.id.tv_details);
            web_view = itemView.findViewById(R.id.web_view);
        }
    }

    class CommentViewHolder extends XRecyclerView.ViewHolder {
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
