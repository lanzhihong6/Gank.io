package com.lanzhihong.gankio.fragment;

import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

/**
 * Created by lanzhihong on 2016/3/4.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
