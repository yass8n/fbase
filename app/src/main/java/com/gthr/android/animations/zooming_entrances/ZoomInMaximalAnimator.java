package com.gthr.android.animations.zooming_entrances;

import android.view.View;

import com.gthr.android.animations.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by yaseen on 2/29/16.
 */
public class ZoomInMaximalAnimator extends BaseViewAnimator {
    @Override
    public void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", 0.5f, 1),
                ObjectAnimator.ofFloat(target,"scaleY",0.5f,1)
        );
    }
}
