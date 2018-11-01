package com.dikanskiy;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().eternalCacheExample();
        //new CacheMain().lifeCacheExample();
    }

    private void eternalCacheExample() throws InterruptedException {
        int size = 3_000_000;
        CacheEngine<Integer, String> cache = new CacheEngineImp<>(size, 0, 0, true);

        for (int i = 0; i < 3_000_000; i++) {
            System.out.println(i);
            cache.put(new MyElement<>(i, "String: " + i));
        }

        for (int i = 0; i < 3_000_000; i++) {
            MyElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }

    private void lifeCacheExample() throws InterruptedException {
        int size = 5;
        CacheEngine<Integer, String> cache = new CacheEngineImp<>(size, 1000, 0, false);

        for (int i = 0; i < size; i++) {
            cache.put(new MyElement<>(i, "String: " + i));
        }

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        Thread.sleep(1000);

        for (int i = 0; i < size; i++) {
            MyElement<Integer, String> element = cache.get(i);
            System.out.println("String for " + i + ": " + (element != null ? element.getValue() : "null"));
        }

        System.out.println("Cache hits: " + cache.getHitCount());
        System.out.println("Cache misses: " + cache.getMissCount());

        cache.dispose();
    }
}
