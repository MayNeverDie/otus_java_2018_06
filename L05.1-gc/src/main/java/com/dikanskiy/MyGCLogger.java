package com.dikanskiy;

import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class MyGCLogger {
    private static final Logger log = Logger.getLogger(MyGCLogger.class.getName());
    private static final Pattern myPattern = Pattern.compile("Copy|Scavenge|ParNew|Young");
    private static long youngCount = 0;
    private static long youngTime = 0;
    private static long oldCount = 0;
    private static long oldTime = 0;

    public static void run() {
        configureLog();
        Timer MyTimer = new Timer();
        MyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                printGCLog();
            }
        }, 60000, 60000);
    }

    private static void configureLog() {
        try {
            LogManager.getLogManager().readConfiguration(MyGCLogger.class.getResourceAsStream("/log.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Start");
    }

    public static void recognizeGC(GarbageCollectorMXBean GC){
        String GCName = GC.getName();
        long count = GC.getCollectionCount();
        if (myPattern.matcher(GCName).find()){
            youngCount += count;
            youngTime += GC.getCollectionTime();
        }else{
            oldCount += count;
            oldTime += GC.getCollectionTime();
        }
    }

    private static void printGCLog() {
        StringBuilder sb = new StringBuilder();
        List<GarbageCollectorMXBean> mxBeans
                = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : mxBeans) {
            long count = gc.getCollectionCount();
            if (count >= 0) {
                recognizeGC(gc);
            }


            sb.append("MinorGC -> Count: ").append(youngCount)
                    .append(", Time (ms): ").append(youngTime)
                    .append(", MajorGC -> Count: ").append(oldCount)
                    .append(", Time (ms): ").append(oldTime);
        }
        log.info(sb.toString());
    }

}
