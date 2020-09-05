package com.example.cpjjh.dots;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class GameView extends View {
    private Game game;
    private Paint defaultBrush, textPaint;
    private Handler handler;
    private boolean animating;
    private Runnable update = new Runnable() {
        @Override
        public void run() {
            invalidate();
            if (animating) {
                game.move();
                handler.postDelayed(update, 24);
            }
        }
    };

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        defaultBrush = new Paint();
        defaultBrush.setStrokeWidth(4);
        defaultBrush.setColor(Color.GREEN);
        textPaint = new Paint();
        textPaint.setTextSize(100);
        textPaint.setColor(Color.BLACK);

        handler = new Handler();
    }

    public void startAnimating() {
        animating = true;
        handler.post(update);
    }

    public void stopAnimating() {
        animating = false;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    protected void onSizeChanged(int width, int height,
                                 int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        game.reset(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (game == null) return;
        if (game.over()) {
            canvas.drawText("Game over!", 0, 200, textPaint);
            stopAnimating();
            return;
        }
        for (Dot dot : game.getDots()) {
            canvas.drawCircle(
                    dot.position.x,
                    dot.position.y,
                    dot.radius, defaultBrush
            );
        }
        canvas.drawText("Level: " + game.getCurrentLevel(), 0, 200, textPaint);
    }
}
