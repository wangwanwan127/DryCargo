package com.bwei.www.drycargo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.www.drycargo.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class DrawableAdapter extends RecyclerView.Adapter<DrawableAdapter.ViewHolder> {
    private Context context;
    private ArrayList<String> strings;
    private ArrayList<String> imgList;
    private OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    public interface OnRecyclerViewItemClick {
        void onitemClick(View view, int position, String name);
    }


    public DrawableAdapter(Context context, ArrayList<String> strings, ArrayList<String> imgList) {
        this.context = context;
        this.strings = strings;
        this.imgList = imgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.drawer_rv_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Uri parse = Uri.parse(imgList.get(position));
        holder.img.setImageURI(parse);
        holder.name.setText(strings.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecyclerViewItemClick.onitemClick(view, position, strings.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById(R.id.name);

        }
    }
}
