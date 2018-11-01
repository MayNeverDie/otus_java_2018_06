package com.dikanskiy;

public class MyElement<K,V> {
    private final K key;
    private final V value;
    private final long creationTime;
    private long lastAccessTime;

    public MyElement(K key, V value){
        this.key = key;
        this.value = value;
        this.creationTime = getCurrentTimeMillis();
        this.lastAccessTime = getCurrentTimeMillis();

    }

    protected long getCurrentTimeMillis(){
        return System.currentTimeMillis();
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return value;
    }

    public long getCreationTime(){
        return creationTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setAccessed(){
        lastAccessTime = getCurrentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MyElement<?, ?> element = (MyElement<?, ?>) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(creationTime, element.creationTime)
                .append(lastAccessTime, element.lastAccessTime)
                .append(key, element.key)
                .append(value, element.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(key)
                .append(value)
                .append(creationTime)
                .append(lastAccessTime)
                .toHashCode();
    }
}
