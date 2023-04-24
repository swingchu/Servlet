package com.example;

import java.util.*;
public class TestModel {
    private Map<String, String> messages = new HashMap<>();

    public TestModel() {
        messages.put("A", "Hello");
        messages.put("B", "Welcome");
        messages.put("C", "Hi");
    }
    
    public String doHello(String user) {
        String message = messages.get(user);
        if (message == null) {
            message="Nice to meet you";
        }
        return String.format("%s, %s!", message, user);
    }
}
