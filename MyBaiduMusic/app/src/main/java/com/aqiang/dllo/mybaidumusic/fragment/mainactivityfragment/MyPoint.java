package com.aqiang.dllo.mybaidumusic.fragment.mainactivityfragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dllo on 16/11/29.
 */

public class MyPoint extends View {
    private int r = 8;
    private boolean isSelected = false;

    public MyPoint(Context context) {
        super(context);
    }

    public MyPoint(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPoint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSelected(boolean selected) {
        isSelected = selected;
        invalidate();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        if (isSelected){
            paint.setColor(Color.BLACK);
        }else{
            paint.setColor(Color.WHITE);
        }
        canvas.drawCircle(getWidth()/2,getHeight()/2,r,paint);
    }
}
