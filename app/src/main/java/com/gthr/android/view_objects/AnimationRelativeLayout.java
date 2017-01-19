package com.gthr.android.view_objects;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by yass8n on 6/1/15.
 */
public class AnimationRelativeLayout extends RelativeLayout {

    public AnimationRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
    }
}
