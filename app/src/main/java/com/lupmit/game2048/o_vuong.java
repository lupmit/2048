package com.lupmit.game2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class o_vuong extends TextView {
    public o_vuong(Context context) {
        super(context);
    }

    public o_vuong(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public o_vuong(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dai = getMeasuredWidth();
        setMeasuredDimension(dai, dai);
    }

    public void settext(int so) {
        if(so <100) {
            setTextSize(40);
        }else if(so<1000) {
            setTextSize(35);
        }else {
            setTextSize(30);
        }

        setTextColor(Color.WHITE);

        GradientDrawable drawable = (GradientDrawable) this.getBackground();
        drawable.setColor(maingame.getDatagame().color(so));
        setBackground(drawable);

        if(so == 0) {
            setText(" ");
        }else setText(""+so);
    }
}
