package com.example.hyunjinkim.twitterdemohj;

class Background {
    public static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}