package com.arg.user.user.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Utils {

    public int add(int a, int b) {
        return a + b;
    }

    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        names.add("Ranjeet");
        names.add("Kumar");
        return names;
    }

    public boolean login(String email, String password) {
        return isValidEmail(email) && password.equals("test");
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.endsWith(".com");
    }

    public static String formatName(String name) {
        return name == null ? "" : name.trim().toUpperCase();
    }

   
    public static boolean isProduction() {
        // Imagine this checks some config or env variable
        return true;
    }
}
