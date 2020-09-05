package com.example.nangshwekyi.drummachineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class Display extends View {

    //private String message;
    //private Paint paint;
    private ImageView imageView;

    public Display(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
       // message = "Display";
        // paint = new Paint();
       // paint.setTextSize(100);
        // paint.setTextAlign(Paint.Align.CENTER);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.drum_photo);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private PointF getCenter(){
        return new PointF(getWidth() /2f, getHeight() /2f);


    }



    public Position getPosition(float x, float y){
        PointF center = getCenter();

        if (x < center.x && y < center.y){
            return Position.TOP_LEFT;
        } else if(x < center.x && y > center.y) {
            return Position.BOTTOM_LEFT;
        }else if(x > center.x && y < center.y){
            return Position.TOP_RIGHT;
        }else if(x > center.x && y > center.y){
            return Position.BOTTOM_RIGHT;
        }else{
            return Position.MIDDLE;
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        PointF center = getCenter();

    }
}
