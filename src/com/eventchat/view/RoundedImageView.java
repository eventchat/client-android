package com.eventchat.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RoundedImageView extends ImageView {

    private Path mPath = null;

    private Paint mPaint = null;

    private PorterDuffXfermode mDuffXfermode = null;

    public RoundedImageView(Context context) {
        super(context);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDuffXfermode = new PorterDuffXfermode(Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final float halfWidth = canvas.getWidth() / 2;
        final float halfHeight = canvas.getHeight() / 2;
        final float radius = Math.max(halfWidth, halfHeight);
        mPath.addCircle(halfWidth, halfHeight, radius, Path.Direction.CCW);
        mPaint.setXfermode(mDuffXfermode);
        canvas.drawPath(mPath, mPaint);
    }
}
