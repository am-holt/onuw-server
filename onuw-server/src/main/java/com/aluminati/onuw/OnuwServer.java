package com.aluminati.onuw;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class OnuwServer {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new OnuwServer().getGreeting());

    }
}
