package com.bwei.www.drycargo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.bean.ListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/18.
 */

public class CargoListAdapter extends RecyclerView.Adapter<CargoListAdapter.ViewHolder> {
    private Context context;
    private List<ListBean.ResultsBean> results;
    private String s;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    public interface OnRecyclerViewItemClick {
        void onitemClick(View view, String url);
    }

    public CargoListAdapter(Context context, List<ListBean.ResultsBean> results) {
        this.context = context;
        this.results = results;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListBean.ResultsBean resultsBean = results.get(position);
        if (resultsBean.getImages() != null) {
            List<String> images = resultsBean.getImages();
            for (int i = 0; i < images.size(); i++) {
                s = images.get(i);
            }
            holder.img.setImageURI(s);
        } else {
//            "res://drawable/" + R.drawable.drawer_icon_client
//            G:\workSpace\DryCargo\app\src\main\res\drawable\img404.png
            Uri parse = Uri.parse("res://drawable/" + R.drawable.img404);
            holder.img.setImageURI(parse);
        }
        holder.desc.setText(resultsBean.getDesc());
        holder.name.setText(resultsBean.getWho());
        holder.date.setText(resultsBean.getCreatedAt());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerViewItemClick.onitemClick(view, resultsBean.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView desc;
        private final TextView name;
        private final TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
            desc = (TextView) itemView.findViewById(R.id.desc);
            name = (TextView) itemView.findViewById(R.id.name);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
