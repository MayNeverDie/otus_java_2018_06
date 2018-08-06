package com.dikanskiy.l21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ObjectFactory {
    static <T extends String> Supplier<Object> get(T value) {
        return () -> new String(value);
    } //24 bytes

    static <T extends Integer> Supplier<Object> get(T value) {
        return () -> new Integer(value);
    } //16 bytes

    static <T extends Long> Supplier<Object> get(T value) {return () -> new Long(value);
    } //24 bytes

    static <T extends Integer> Supplier<Object> getLinkedList(T value, int size) {
        return ()->Stream.generate(()->(get(value)
                .get())).limit(size)
                .collect(Collectors.toCollection(LinkedList::new));
    } //32 bytes

    static <T extends Integer> Supplier<Object> getArrayList(T value, int size) {
        return ()->Stream.generate(()->(get(value)
                .get())).limit(size)
                .collect(Collectors.toList());
    } //24 bytes

    static <T extends Integer> Supplier<Object> getHashSet(T value, int size) {
        return ()->Stream.generate(()->(get(value)
                .get())).limit(size)
                .collect(Collectors.toCollection(HashSet::new));
    } //64 bytes
}



