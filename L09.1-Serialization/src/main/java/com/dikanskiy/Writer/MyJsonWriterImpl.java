package com.dikanskiy.Writer;

import com.dikanskiy.Visitor.JsonVisitor;

import java.lang.reflect.Field;

public class MyJsonWriterImpl implements MyJsonWriter {
    private JsonVisitor myVis;

    public MyJsonWriterImpl() {
        this.myVis = new JsonVisitor();
    }

    public String toString(Object object) {
        if (object != null) {
            try {
                for (Field field : object.getClass().getDeclaredFields()
                ) {
                    field.setAccessible(true);
                    myVis.visit(field.get(object), field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        return myVis.getJsonObject().toString();
    }
}
