package com.dikanskiy;

import com.dikanskiy.Writer.MyJsonWriter;
import com.dikanskiy.Writer.MyJsonWriterImpl;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        EasyObject objectOne = new EasyObject();

        MyJsonWriter mjw = new MyJsonWriterImpl();
        String serializedObject = mjw.toString(objectOne);
        System.out.println(serializedObject);

        Gson gson = new Gson();
        System.out.println(gson.toJson(objectOne));
        EasyObject objectTwo = gson.fromJson(serializedObject, EasyObject.class);
        System.out.println(objectOne.equals(objectTwo));
    }
}
