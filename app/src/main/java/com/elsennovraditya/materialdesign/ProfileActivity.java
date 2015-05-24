package com.elsennovraditya.materialdesign;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by elsen on 4/6/15.
 */
public class ProfileActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    @InjectView(R.id.profile_cover)
    ImageView profileCover;

    @InjectView(R.id.scroll)
    ObservableScrollView scrollView;

    @InjectView(R.id.toolbar)
    Toolbar mToolbarView;

    private int parallaxImageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.inject(this);

        mToolbarView.setBackgroundColor(
                ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.primary)));
        setSupportActionBar(mToolbarView);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        parallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.cover_parallax_height);
        scrollView.setScrollViewCallbacks(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(scrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int i, boolean b, boolean b2) {
        int baseColor = getResources().getColor(R.color.primary);
        float alpha = 1 - (float) Math.max(0, parallaxImageHeight - i) / parallaxImageHeight;
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(profileCover, i / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

}
