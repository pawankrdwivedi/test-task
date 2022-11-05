package com.test.task.framework.utils;

import java.util.Random;

public class Utils {
    public static void wait(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomNumber(int min, int max) {
        Random randomGenerator = new Random();
        int number = randomGenerator.nextInt(max - min) + min;
        return Integer.toString(number);
    }

    public static String printErrorMessage(String message) {
        return message;
        //message.substring(0,120);
    }
}
