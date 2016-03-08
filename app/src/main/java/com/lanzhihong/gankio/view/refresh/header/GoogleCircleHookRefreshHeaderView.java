package com.lanzhihong.gankio.view.refresh.header;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.lanzhihong.gankio.R;
import com.lanzhihong.gankio.view.refresh.GoogleCircleProgressView;

/**
 * Created by aspsine on 15/11/7.
 */
public class GoogleCircleHookRefreshHeaderView extends FrameLayout implements SwipeTrigger, SwipeRefreshTrigger {
    private GoogleCircleProgressView progressView;

    private int mTriggerOffset;

    private int mFinalOffset;

    public GoogleCircleHookRefreshHeaderView(Context context) {
        this(context, null);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleCircleHookRefreshHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.refresh_trigger_offset_google);
        mFinalOffset = context.getResources().getDimensionPixelOffset(R.dimen.refresh_final_offset_google);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        progressView = (GoogleCircleProgressView) findViewById(R.id.googleProgress);
        progressView.setColorSchemeResources(
                R.color.colorBluePrimary,
                R.color.colorRedPrimary,
                R.color.colorIndigoPrimary,
                R.color.colorLimePrimary);
//        progressView.setColorSchemeColors(Color.parseColor("#7B1FA2"));
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onRefresh() {
        progressView.start();
    }

    @Override
    public void onPrepare() {
        progressView.setStartEndTrim(0, (float) 0.75);
    }

    @Override
    public void onSwipe(int y, boolean isComplete) {
        float alpha = y / (float) mTriggerOffset;
        ViewCompat.setAlpha(progressView, alpha);
        progressView.setProgressRotation(y / (float) mFinalOffset);
    }

    @Override
    public void onRelease() {

    }

    @Override
    public void complete() {
        progressView.stop();
    }

    @Override
    public void onReset() {
        ViewCompat.setAlpha(progressView, 1f);
    }

}
