package com.dikanskiy.Visitor;

import com.google.gson.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class VisitorImpl implements Visitor {
    JSONObject jsonObject;

    public VisitorImpl() {
        this.jsonObject = new JSONObject();
    }

    public void visit(Object o, String name) {
        try {
            Method method = getMethod(o.getClass(), name);
            method.invoke(this, new Object[] {o,name});
        } catch (Exception e) { }
    }

    private void visitObject(Object object, String name){
        jsonObject.put(name,object);
    }

    private void visitMap(Map map, String name){
        JSONObject jsonOfMap = new JSONObject();
        jsonOfMap.putAll(map);
        jsonObject.put(name, jsonOfMap);
    }

    private void visitList(List list, String name){
        JSONArray jsonArray = new JSONArray();
        list.forEach(Item->jsonArray.add(Item));
        jsonObject.put(name,jsonArray);
    }

    private void visitNumber(Number number, String name){
        jsonObject.put(name,number);
    }

    private void visitBoolean(Boolean bool, String name){
        jsonObject.put(name,bool);
    }

    private void visitCharacter(Character character, String name){
        jsonObject.put(name,character.toString());
    }

    protected Method getMethod(Class c, String name) {
        Class newc = c;
        Method m = null;
        // Try the superclasses
        while (m == null && newc != Object.class) {
            String method = newc.getName();
            method = "visit" + method.substring(method.lastIndexOf('.') + 1);
            try {
                m = getClass().getDeclaredMethod(method, new Class[] {newc,String.class});
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
                    m = getClass().getDeclaredMethod(method, new Class[] {interfaces[i],String.class});
                } catch (NoSuchMethodException e) {}
            }
        }
        if (m == null) {
            try {
                m = getClass().getDeclaredMethod("visitObject", new Class[] {Object.class,String.class});
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
