package com.gthr.android.view_objects;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import com.gthr.android.R;

/**
 * Created by noshaf on 3/3/15.
 */
public class fEditText extends EditText {
    public boolean keyboard_dismissed = false;
    public boolean is_dismissable = false;
    public boolean drop_keyboard = true;
    public int position; //for sign in activity

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && is_dismissable) {
            this.keyboard_dismissed = true;
            this.clearFocus(); //this will call the on focus change listener for event_chat_list_text
            return drop_keyboard;
        }

        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
            return true;
        }

        return false;
    }

    public fEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        parseAttributes(context, attrs);
    }

    public fEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        parseAttributes(context, attrs);
    }

    public fEditText(Context context) {
        super(context);
        init();
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