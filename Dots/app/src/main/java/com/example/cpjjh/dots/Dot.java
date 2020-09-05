package com.example.cpjjh.dots;

import android.graphics.PointF;

class Dot {
    PointF position;
    float radius;
    private float originalRadius;
    float speed;

    Dot(float x, float y, int size) {
        position = new PointF(x, y);
        originalRadius = size;
        this.radius = size;
        speed = 1;
    }

    void touch() {
        radius = radius / 1.2f;
    }

    boolean isPopped() {
        return radius < (originalRadius / 4);
    }

    boolean within(int width, int height) {
        float left = position.x - radius;
        float top = position.y - radius;
        float right = position.x + radius;
        float bottom = position.y + radius;
        return left >= 0 && right <= width
                && top >= 0 && bottom <= height;
    }

    boolean near(float x, float y) {
        double distance = Math.sqrt(Math.pow(position.x - x, 2) + Math.pow(position.y - y, 2));
        return distance < radius;
    }
}
