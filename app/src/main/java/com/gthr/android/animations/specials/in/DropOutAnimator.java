package com.gthr.android.animations.specials.in;

import android.view.View;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.gthr.android.animations.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

public class DropOutAnimator extends BaseViewAnimator{
    @Override
    protected void prepare(View target) {
        int distance = target.getTop() + target.getHeight();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 1),
                Glider.glide(Skill.BounceEaseOut, getDuration(), ObjectAnimator.ofFloat(target, "translationY", -distance, 0))
        );
    }
}
