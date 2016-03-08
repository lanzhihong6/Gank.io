package com.lanzhihong.gankio.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.adapter.MyAndroidRecyclerViewAdapter;
import com.lanzhihong.gankio.bean.Android;
import com.lanzhihong.gankio.util.ApiManger;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AndroidFragment extends BaseFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Android.ResultsEntity> results;
    private ProgressDialog dialog;
    private RecyclerView recyclerView;
    private int pageSize = 10;
    private int page = 1;
    private MyAndroidRecyclerViewAdapter adapter;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;

    public AndroidFragment() {

    }

    @SuppressWarnings("unused")
    public static AndroidFragment newInstance(int columnCount) {
        AndroidFragment fragment = new AndroidFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_android_list, container, false);
        ButterKnife.bind(this, view);

        mListener = new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Android.ResultsEntity item) {

            }
        };

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

        recyclerView = (RecyclerView) view.findViewById(R.id.swipe_target);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), mColumnCount));
        }

        showLoading();
        getData();

        return view;
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
        void onListFragmentInteraction(Android.ResultsEntity item);
    }

    /**
     * 加载下一页
     */
    private void getMoreData() {
        page++;
        getData();
        ;
    }

    /**
     * 刷新当前页
     */
    private void refreshData() {
        page = 1;
        getData();
    }

    /**
     * 网络请求,获取Android数据
     */
    private void getData() {
        ApiManger.getInstance().getService().getAndroidList(pageSize, page)
                .subscribeOn(Schedulers.io())
//                .retry()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Android>() {
                    @Override
                    public void onNext(Android android) {
                        if (adapter == null) {
                            adapter = new MyAndroidRecyclerViewAdapter(android.getResults(), mListener);
                            recyclerView.setAdapter(adapter);
                        } else {
                            adapter.addData(android.getResults());
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCompleted() {
                        dismissDialog();
                        stopLoading();
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d("lanzhihong", Log.getStackTraceString(error));
                        error.printStackTrace();
                        dismissDialog();
                        stopLoading();
                    }
                });

    }

    private void showLoading() {
        dialog = ProgressDialog.show(getActivity(), "请稍后", "网络请求中...");
    }

    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }

    }

    private void stopLoading() {
        if (mSwipeToLoadLayout != null) {
            mSwipeToLoadLayout.setRefreshing(false);
            mSwipeToLoadLayout.setLoadingMore(false);
        }

    }


}
