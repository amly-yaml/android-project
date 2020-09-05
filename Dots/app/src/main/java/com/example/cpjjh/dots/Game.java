package com.example.cpjjh.dots;

import java.util.List;
import java.util.Random;
import java.util.Vector;

class Game {
    private List<Dot> dots;
    private static final Random random = new Random();
    private int currentLevel;
    private int width, height;

    Game() {
        dots = new Vector<>();
    }

    void reset(int width, int height) {
        currentLevel = 1;
        generateLevel(width, height);
    }

    private void generateLevel(int width, int height) {
        this.width = width;
        this.height = height;

        dots.clear();
        for (int i = 0; i < currentLevel * 6; ++i) {
            int radius = 300 / currentLevel;
            float x = radius * 2 + random.nextInt(width - radius * 2);
            float y = radius * 2 + random.nextInt(height - radius * 2);
            Dot dot = new Dot(x, y, radius);
            dot.speed = 10 + random.nextInt(currentLevel * 20);
            dots.add(dot);
        }
    }

    void move() {
        for (Dot dot : dots) {
            float dx = dot.speed * (random.nextFloat() - 0.5f);
            float dy = dot.speed * (random.nextFloat() - 0.5f);
            dot.position.offset(dx, dy);
            if (!dot.within(width, height)) {
                dot.position.offset(-dx, -dy); // prevent moving outside bounds
            }
        }
    }

    List<Dot> getDots() {
        return dots;
    }

    String getCurrentLevel() {
        return "" + currentLevel;
    }

    boolean touch(float x, float y) {
        List<Dot> toRemove = new Vector<>();
        for (Dot dot : dots) {
            if (dot.near(x, y)) {
                dot.touch();
            }
            if (dot.isPopped()) {
                toRemove.add(dot);
            }
        }
        dots.removeAll(toRemove);
        if (dots.size() == 0) {
            currentLevel = currentLevel + 1;
            generateLevel(width, height);
            return true;
        }
        return false;
    }

    boolean over() {
        return currentLevel == 7;
    }
}
