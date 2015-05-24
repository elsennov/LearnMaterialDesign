package com.elsennovraditya.materialdesign;

import com.facebook.shimmer.ShimmerFrameLayout;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by elsen on 4/6/15.
 */
public class ImageDetailActivity extends AppCompatActivity {

    @InjectView(R.id.shimmer_view_container)
    ShimmerFrameLayout shimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.inject(this);
//        shimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onBackPressed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        super.onBackPressed();
    }
}
