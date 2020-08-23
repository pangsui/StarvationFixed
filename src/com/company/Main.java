package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) {

        Thread t1 = new Thread(new Starvation(ThreadColor.ANSI_GREEN),"priority 10");
        Thread t2 = new Thread(new Starvation(ThreadColor.ANSI_PURPLE),"priority 8");
        Thread t3 = new Thread(new Starvation(ThreadColor.ANSI_CYAN),"priority 6");
        Thread t4 = new Thread(new Starvation(ThreadColor.ANSI_RED),"priority 2");
        Thread t5 = new Thread(new Starvation(ThreadColor.ANSI_BLUE),"priority 1");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(2);
        t5.setPriority(1);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();


    }

    public static class Starvation implements Runnable{
        private String color;
        private int count = 1;

        public Starvation(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        @Override
        public void run() {
            for (int i=0; i<100; i++) {
                lock.lock();
                try{
                    System.out.format(color + "%s %d\n ", Thread.currentThread().getName(), count++);
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
