package com.dikanskiy.l21;
import java.util.function.Supplier;

public class Measurer {
    public static void measure(Supplier<Object> supplier) throws InterruptedException {
        long initialMem = getUsedMemory();
        System.out.println("Initial memory: " + initialMem);

        int arraySize = 10_000_000;

        Object[] array = new Object[arraySize];
        long referenceMem = getUsedMemory();
        System.out.println("Reference memory: " + (referenceMem - initialMem) / array.length);
        for (int i = 0; i < arraySize; i++) {
            array[i] = supplier.get();
        }
        long currentMem = getUsedMemory();
        System.out.println("Object memory: " + (currentMem - referenceMem) / array.length);
    }

    public static long getUsedMemory() throws InterruptedException {
        System.gc(); //run garbage collector;
        Thread.sleep(1000);
        Runtime runtime = Runtime.getRuntime(); //getting current Runtime
        return runtime.totalMemory() - runtime.freeMemory(); //Used memory
    }
}
