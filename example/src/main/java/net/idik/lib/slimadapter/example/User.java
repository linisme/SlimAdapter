package net.idik.lib.slimadapter.example;

/**
 * Created by linshuaibin on 03/04/2017.
 */

public class User {
    private String name;
    private String phone;
    private int age;
    private int avatarRes;

    public User(String name, int age, int avatarRes, String phone) {
        this.name = name;
        this.age = age;
        this.avatarRes = avatarRes;
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public int getAvatarRes() {
        return avatarRes;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
