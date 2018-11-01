package com.dikanskiy;

import java.lang.ref.SoftReference;
import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class CacheEngineImp<K,V> implements CacheEngine<K,V> {
    private static final int TIME_THRESHOLD_MS = 5;

    private int hit;
    private int miss;
    private final int maxElements;
    public final long lifeTime;
    public final long idleTime;
    private final boolean isEternal;

    private final Map<K, SoftReference<MyElement<K,V>>> elements = new LinkedHashMap<>();
    private final Timer timer = new Timer();

    public CacheEngineImp(int maxElements, long lifeTime, long idleTime, boolean isEternal){
        this.maxElements = maxElements;
        this.lifeTime = lifeTime;
        this.idleTime = idleTime;
        this.isEternal = isEternal;
    }

    public void put(MyElement<K,V> element) {
        if(elements.size() == maxElements){
            K firstKey = elements.keySet().iterator().next();
            elements.remove(firstKey);
        }

        K elementKey = element.getKey();
        elements.put(elementKey, new SoftReference(element));

        if(!isEternal){
            if (lifeTime!=0){
                TimerTask lifeTimerTask = getTimerTask(elementKey, lifeElement -> lifeElement.getCreationTime() + lifeTime);
                timer.schedule(lifeTimerTask, lifeTime);
            }
            if (idleTime!=0){
                TimerTask idleTimerTask = getTimerTask(elementKey, idleElement -> idleElement.getLastAccessTime() + idleTime);
                timer.schedule(idleTimerTask, idleTime, idleTime);
            }
        }

    }

    public MyElement get(Object key) {
        SoftReference<MyElement<K,V>> element = elements.get(key);
        if (element != null){
            hit++;
        }else{
            miss++;
        }
        return  element.get();
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    public void dispose() {
        timer.cancel();
    }

    private TimerTask getTimerTask(final K key, Function<MyElement<K,V>, Long> timeFunction){
        return new TimerTask() {
            @Override
            public void run() {
                SoftReference<MyElement<K,V>> element = elements.get(key);
                if ( element==null || isT1BeforeT2(timeFunction.apply(element.get()),System.currentTimeMillis())){
                    elements.remove(key);
                    this.cancel();
                }
            }
        };

    }

    private boolean isT1BeforeT2(long t1, long t2){
        return t1<t2 + TIME_THRESHOLD_MS;
    }
}
