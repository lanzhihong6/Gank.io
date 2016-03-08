package com.lanzhihong.gankio.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.bean.Android;
import com.lanzhihong.gankio.fragment.AndroidFragment.OnListFragmentInteractionListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyAndroidRecyclerViewAdapter extends RecyclerView.Adapter<MyAndroidRecyclerViewAdapter.ViewHolder> {

    private final List<Android.ResultsEntity> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyAndroidRecyclerViewAdapter(List<Android.ResultsEntity> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_android, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.desc.setText(mValues.get(position).getDesc());
        holder.time.setText(mValues.get(position).getWho() + "   " + mValues.get(position).getCreatedAt());

        //HTML a标签  点击跳转浏览器
        String url = mValues.get(position).getUrl();
        String html = "<a href='" + url + "'>" + url + "</a>";
        CharSequence charSequence = Html.fromHtml(html);
        holder.url.setText(charSequence);
        holder.url.setMovementMethod(LinkMovementMethod.getInstance());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    public void addData(List<Android.ResultsEntity> values) {
        if (mValues != null) {
            mValues.addAll(values);
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.desc)
        TextView desc;
        @Bind(R.id.url)
        TextView url;
        @Bind(R.id.time)
        TextView time;
        public final View mView;
        public Android.ResultsEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

    }
}
