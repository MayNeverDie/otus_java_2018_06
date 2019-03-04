package com.dikanskiy;

import java.util.*;

public class EasyObject {
    private boolean[] boolArray;
    private boolean boolField = false;
    private byte byteField = 0;
    private short shortField = 1;
    private char charField = 2;
    private int integerField = 3;
    private long longField = 4L;
    private double doubleField = 5;


    private List listField;
    private HashMap<Integer, String> mapField;
    private String stringField = "str";

    public EasyObject() {
        boolArray = new boolean[]{false, true};
        listField = new ArrayList();
        listField.add("list string one");
        listField.add("list string two");
        mapField = new HashMap();
        mapField.put(1, "map string one");
        mapField.put(2, "map string two");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EasyObject object = (EasyObject) o;
        return boolField == object.boolField &&
                byteField == object.byteField &&
                shortField == object.shortField &&
                charField == object.charField &&
                integerField == object.integerField &&
                longField == object.longField &&
                Double.compare(object.doubleField, doubleField) == 0 &&
                Objects.equals(listField, object.listField) &&
                Objects.equals(mapField, object.mapField) &&
                Objects.equals(stringField, object.stringField);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boolField, byteField, shortField, charField, integerField, longField, doubleField, listField, mapField, stringField);
    }
}
