package com.example.nangshwekyi.cp3406assignment2app1;


class Background {
    public static void run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
