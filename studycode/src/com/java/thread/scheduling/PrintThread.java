package com.java.thread.scheduling;

class PrintThread extends Thread {

    @Override
    public void run() {
        int index = 0;
        while (true) {
            System.out.println(++index);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }

    }
}
