package com.java.thread.scheduling;

import java.util.Scanner;

public class QuitThread extends Thread {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            int out = scanner.nextInt();
            if (out == 0) {
                System.exit(0);
                System.out.println("실제 프로그램이 종료됩니다");
            }
        }
    }
}
