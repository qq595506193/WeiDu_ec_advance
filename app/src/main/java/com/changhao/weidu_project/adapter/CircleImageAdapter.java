package com.changhao.weidu_project.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changhao.weidu_project.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class CircleImageAdapter extends RecyclerView.Adapter<CircleImageAdapter.ViewHolder> {
    private Context context;
    private List<String> images;

    public CircleImageAdapter(Context context) {
        images = new ArrayList<>();
        this.context = context;
    }

    public void setImages(List<String> images) {
        if (images != null) {
            this.images = images;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CircleImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_circle_images, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CircleImageAdapter.ViewHolder viewHolder, int i) {
        String s = images.get(i);
        Uri uri = Uri.parse(s);
        viewHolder.circle_images_icon.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return images == null ? 0 : images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView circle_images_icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circle_images_icon = itemView.findViewById(R.id.circle_images_icon);
        }
    }
}
