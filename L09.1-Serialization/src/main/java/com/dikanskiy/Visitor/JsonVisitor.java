package com.dikanskiy.Visitor;

//import com.google.gson.JsonObject;

import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class JsonVisitor implements Visitor {
    private JSONObject jsonObject;

    public JsonVisitor() {
        this.jsonObject = new JSONObject();
    }

    public void visit(Object o, String fieldName) {
        try {
            Method method = getMethod(o.getClass());
            method.invoke(this, new Object[]{o, fieldName});
            if(o instanceof Visitor){
                callAccept((Visitable) o);
            }
        } catch (Exception e) {
        }
    }

    public void callAccept(Visitable visitable) {
        visitable.accept(this);
    }

    private void visitObject(Object object, String fieldName) {
        jsonObject.put(fieldName, object);
    }

    private void visitArray(Array array, String fieldName) {
        JSONArray jsonArray = new JSONArray();

    }

    private void visitMap(Map map, String fieldName) {
        JSONObject jsonOfMap = new JSONObject();
        jsonOfMap.putAll(map);
        jsonObject.put(fieldName, jsonOfMap);
    }

    private void visitList(List list, String fieldName) {
        JSONArray jsonArray = new JSONArray();
        list.forEach(Item -> jsonArray.add(Item));
        jsonObject.put(fieldName, jsonArray);
    }

    private void visitNumber(Number number, String fieldName) {
        jsonObject.put(fieldName, number);
    }

    private void visitBoolean(Boolean bool, String fieldName) {
        jsonObject.put(fieldName, bool);
    }

    private void visitCharacter(Character character, String fieldName) {
        jsonObject.put(fieldName, character.toString());
    }

    protected Method getMethod(Class c) {
        Class newc = c;
        Method m = null;
        // Try the superclasses
        while (m == null && newc != Object.class) {
            String method = newc.getName();
            method = "visit" + method.substring(method.lastIndexOf('.') + 1);
            try {
                m = getClass().getDeclaredMethod(method, new Class[]{newc, String.class});
                m.setAccessible(true);
            } catch (NoSuchMethodException e) {
                newc = newc.getSuperclass();
            }
        }
        if (newc == Object.class) {
            Class[] interfaces = c.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                String method = interfaces[i].getName();
                method = "visit" + method.substring(method.lastIndexOf('.') + 1);
                try {
                    m = getClass().getDeclaredMethod(method, new Class[]{interfaces[i], String.class});
                } catch (NoSuchMethodException e) {
                }
            }
        }
        if (m == null) {
            try {
                m = getClass().getDeclaredMethod("visitObject", new Class[]{Object.class, String.class});
            } catch (Exception e) {
                // Can't happen
            }
        }
        return m;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
