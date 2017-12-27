package com.bwei.www.drycargo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bwei.www.drycargo.R;
import com.bwei.www.drycargo.bean.ListBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/19.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context context;
    private List<ListBean.ResultsBean> resultslist;

    public GridAdapter(Context context, List<ListBean.ResultsBean> resultslist) {
        this.context = context;
        this.resultslist = resultslist;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.grid_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListBean.ResultsBean resultsBean = resultslist.get(position);
        String url = resultsBean.getUrl();
        holder.img.setImageURI(url);
    }

    @Override
    public int getItemCount() {
        return resultslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.img);
        }
    }
}
