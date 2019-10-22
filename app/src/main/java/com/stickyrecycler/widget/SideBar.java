package com.stickyrecycler.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SideBar extends View {

    // 26个字母
    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"
    };

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private int length;
    private int letterHeight;
    private Paint paint;

    private void initView() {
        paint = new Paint();
        length = letters.length;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        letterHeight = height / length;
        for (int i = 0; i < length; i++) {
            // 所有字母的默认颜色 目前为灰色(右侧字体颜色)
            paint.setColor(Color.parseColor("#333333"));
            //(右侧字体样式)
            paint.setTypeface(Typeface.DEFAULT);
            paint.setAntiAlias(true);
            //(右侧字体大小)
            paint.setTextSize(30);
            //设置是否为粗体文字
            paint.setFakeBoldText(true);
            //x坐标等于=中间-字符串宽度的一般
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = letterHeight * i + letterHeight;
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();//重置画笔
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int position = y / letterHeight - 1;
        if (position >= 0 && position < length) {
            if (onLetterChangedListener != null) {
                onLetterChangedListener.letterChange(letters[position]);
            }
        }
        return true;
    }

    private OnLetterChangedListener onLetterChangedListener;

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void letterChange(String letter);
    }
}
