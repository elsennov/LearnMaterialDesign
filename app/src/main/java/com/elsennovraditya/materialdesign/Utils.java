package com.elsennovraditya.materialdesign;

import com.balysv.materialripple.MaterialRippleLayout;

import android.graphics.Color;
import android.view.View;

/**
 * Created by elsen on 5/11/15.
 */
public class Utils {

    public static void addMaterialRipple(View view) {
        MaterialRippleLayout.on(view)
                .rippleColor(Color.WHITE)
                .rippleAlpha((float) 0.5)
                .rippleDuration(350)
                .rippleFadeDuration(75)
                .create();
    }

}
