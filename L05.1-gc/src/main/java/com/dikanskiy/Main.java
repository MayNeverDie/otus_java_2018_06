package com.dikanskiy;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int size = 500_000;

    public static void main(String[] args) {
        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        MyGCLogger.run();
        try {
            startMemoryLeak();
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startMemoryLeak() throws InterruptedException {
        int newArraySize = 0;
        while(true){
            int arraySize = size + newArraySize;

            Object[] myArray = new Object[newArraySize];
            Thread.sleep(3000);
            for (int i = 0;i<newArraySize;i++){
                myArray[i] = new Object();
            }
            newArraySize += 150_000;
            System.out.println("Created: "+arraySize+" objects" );
        }
    }
}
