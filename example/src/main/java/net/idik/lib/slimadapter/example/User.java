package net.idik.lib.slimadapter.example;

/**
 * Created by linshuaibin on 03/04/2017.
 */

public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
