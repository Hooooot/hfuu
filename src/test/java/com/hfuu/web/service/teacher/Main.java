package com.hfuu.web.service.teacher;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author :whh0987@foxmail.com
 * 最后修改时间：
 * 最后修改人：
 * @Description :
 * @date :2019/10/8 16:57
 */
public class Main {
    private int id;
    private String name;
    private List friends;

    @Test
    public void test() throws IllegalAccessException, IOException {
        String s = "files\\c0d108108e1ff87bbfa8b4e67633efa5:4564.jpg";
        String sub = s.substring(0, s.indexOf(':'));
        System.out.println(sub);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getFriends() {
        return friends;
    }

    public void setFriends(List friends) {
        this.friends = friends;
    }
}
