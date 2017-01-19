package com.gthr.android.animations.Shrinking;

import android.view.View;

import com.gthr.android.animations.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by yaseen on 2/24/16.
 */
public class ShrinkingVerticalAnimator extends BaseViewAnimator {
    @Override
    public void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "scaleY", 1, 0.01f)
        );
    }
}
