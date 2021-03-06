package com.example.nangshwekyi.photoeditorapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


public class MyView extends View {

    private Paint paint;
    private int red, green, blue;
    private Random random;
    private ArrayList<PointF> points;


    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        random = new Random();
        green = 255;
        paint.setColor(Color.argb(255, red, green, blue));
        setBackgroundColor(Color.GREEN);

    }

    public void setPoints(ArrayList<PointF> points) {
        this.points = points;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (points == null) return;

        for (PointF point : points) {
            RectF rect = new RectF(point.x, point.y, point.x + 40, point.y + 40);
            canvas.drawRect(rect, paint);
        }

    // update paints color

        switch(random.nextInt(3)){
        case 0:
            red = (red + 1) % 256;
            break;
        case 1:
            green = (green + 1) % 256;
            break;
        case 2:
            blue = (blue + 1) % 256;
            break;
    }
    //++red;
    //if (red == 256){
    //  red = 0;
    //++green;
    //if (green == 256){
    //  green = 0;
    //++blue;
    //if (blue == 256){
    //  blue = 0;
    //}
    //}
    //}
    paint.setColor(Color.argb(255,red,green,blue));
    System.out.println(String.format("%d %d %d",red,green,blue));
    }
}
