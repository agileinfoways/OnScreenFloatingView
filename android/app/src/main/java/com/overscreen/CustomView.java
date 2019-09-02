package com.example.lepard.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;

public class CustomView extends View {

    private Paint paint;
    private Paint shaderPaint;
    private int sqaureDimen;

    public CustomView(Context context) {
        super(context);

        // create the Paint and set its color        
        paint = new Paint();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        initShaderPaint();
        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 3, shaderPaint);

    }

    private void initShaderPaint() {
        if (shaderPaint == null) {
            shaderPaint = new Paint();
            shaderPaint.setStrokeWidth(1);
            shaderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            shaderPaint.setShader(new RadialGradient(getWidth() / 2, getHeight() / 2,
                    getHeight() / 3, Color.YELLOW, Color.parseColor("#24ab3a"), Shader.TileMode.MIRROR));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sqaureDimen = 400;

        int width = sqaureDimen;
        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            width = sqaureDimen;
        } else if ((getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT) || (getLayoutParams().width == ViewGroup.LayoutParams.FILL_PARENT)) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        } else
            width = getLayoutParams().width;


        int height = sqaureDimen;
        if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {

        } else if ((getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT) || (getLayoutParams().height == ViewGroup.LayoutParams.FILL_PARENT)) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else
            height = getLayoutParams().height;

        setMeasuredDimension(width | MeasureSpec.EXACTLY, height | MeasureSpec.EXACTLY);

    }

}