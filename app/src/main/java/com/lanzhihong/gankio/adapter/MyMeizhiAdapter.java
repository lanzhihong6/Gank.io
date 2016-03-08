package com.lanzhihong.gankio.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanzhihong.gankio.GankApplication;
import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.bean.Meizhi;
import com.lanzhihong.gankio.fragment.MeizhiFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyMeizhiAdapter extends RecyclerView.Adapter<MyMeizhiAdapter.ViewHolder> {
    private List<Meizhi.ResultsEntity> mValues;
    private OnListFragmentInteractionListener mListener = null;


    public MyMeizhiAdapter(List<Meizhi.ResultsEntity> list, OnListFragmentInteractionListener listener) {
        mValues = list;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_meizhi, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.mItem = mValues.get(position);
        int screenWidth = GankApplication.getContext().getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams lp = viewHolder.img.getLayoutParams();
        lp.width = screenWidth;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        viewHolder.img.setLayoutParams(lp);
        viewHolder.img.setMaxWidth(screenWidth);
        viewHolder.img.setMaxHeight(screenWidth * 10);

        Glide.with(GankApplication.getContext())
                .load(mValues.get(position).getUrl())
                .into(viewHolder.img);

        viewHolder.text.setText("提供者:" +
                mValues.get(position).getWho());

        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
//                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addData(List<Meizhi.ResultsEntity> results) {
        if (mValues != null) {
            mValues.addAll(results);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView text;
        View mView;
        public Meizhi.ResultsEntity mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            img = (ImageView) itemView.findViewById(R.id.img);
            text = (TextView) itemView.findViewById(R.id.who);
        }

    }

}
