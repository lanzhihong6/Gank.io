package com.lanzhihong.gankio.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.adapter.MyMeizhiAdapter;
import com.lanzhihong.gankio.bean.Meizhi;
import com.lanzhihong.gankio.util.ApiManger;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MeizhiFragment extends BaseFragment {

    private OnListFragmentInteractionListener mListener;
    @Bind(R.id.swipe_target)
    RecyclerView recyclerView;

    private MyMeizhiAdapter adapter;
    private int pageSize = 10;
    private int page = 1;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;


    public MeizhiFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meizhi_list, container, false);
        ButterKnife.bind(this, view);


        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);

        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


        mSwipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getMoreData();
            }
        });
        mSwipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

        getData();

        return view;
    }

    private void refreshData() {
        page = 1;
        getData();
    }

    private void getMoreData() {
        page++;
        getData();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Meizhi.ResultsEntity item);
    }

    /**
     * 网络请求,获取Android数据
     */
    private void getData() {
        Log.d("lanzhihong", "网络请求" + page);
        ApiManger.getInstance().getService().getMeizhiList(pageSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(3)
                .subscribe(new Observer<Meizhi>() {
                    @Override
                    public void onNext(Meizhi meizhi) {
                        stopLoading();
                        Log.d("lanzhihong", "返回==" + meizhi.toString());
                        if (adapter == null) {
                            adapter = new MyMeizhiAdapter(meizhi.getResults(), mListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.addData(meizhi.getResults());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCompleted() {
//                        dismissDialog();
                        stopLoading();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d("lanzhihong", Log.getStackTraceString(error));
                        error.printStackTrace();
//                        dismissDialog();
                        stopLoading();
                    }
                });

    }

    private void stopLoading() {
        if (mSwipeToLoadLayout != null) {
            mSwipeToLoadLayout.setRefreshing(false);
            mSwipeToLoadLayout.setLoadingMore(false);
        }

    }

}
