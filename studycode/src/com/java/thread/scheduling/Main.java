package com.java.thread.scheduling;

public class Main {
    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();
        QuitThread quitThread = new QuitThread();

        printThread.start();
        quitThread.start();
    }
}


