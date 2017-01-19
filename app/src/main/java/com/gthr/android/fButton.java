package com.gthr.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by noshaf on 7/7/16.
 */
public class fButton extends Button {

    public fButton(Context context) {
        super(context);
        init();
    }

    public fButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        parseAttributes(context, attrs);
    }

    public fButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        parseAttributes(context, attrs);
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-reg-webfont.ttf");
            setTypeface(tf);
        }
    }

    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.fTextView);

        //The value 0 is a default, but shouldn't ever be used since the attr is an enum
        int typeface = values.getInt(R.styleable.fTextView_typeface, 0);

        switch(typeface) {
            case 0: default:
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-reg-webfont.ttf"));
                break;
            case 1:
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-bold-webfont.ttf"));
                break;
            case 2:
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-sbold-webfont.ttf"));
                break;
            case 3:
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-light-webfont.ttf"));
                break;
            case 4:
                setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/proximanova-black-webfont.ttf"));
                break;
        }

        values.recycle();
    }
}
