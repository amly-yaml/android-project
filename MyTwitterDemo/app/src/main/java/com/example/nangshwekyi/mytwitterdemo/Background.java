package com.example.nangshwekyi.mytwitterdemo;

class Background {
    public static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
