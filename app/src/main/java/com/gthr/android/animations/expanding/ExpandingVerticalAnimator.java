package com.gthr.android.animations.expanding;

import android.view.View;

import com.gthr.android.animations.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by yaseen on 2/24/16.
 */
public class ExpandingVerticalAnimator extends BaseViewAnimator {
    @Override
    public void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target,"scaleY",0.01f,1),
                ObjectAnimator.ofFloat(target,"alpha",0,1)
        );
    }
}
