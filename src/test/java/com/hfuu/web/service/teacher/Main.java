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
        String s = "files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|files/c7f5e4f6d3d9f84b5a6fbc6d5f8b7309:QQ图片20191017132501.jpg|";
        String na = s.substring(0,s.indexOf(":")+1).replace("|", "");
        System.out.println(na);
        String [] fileName = s.split("\\|");
        for (String name : fileName) {
            String n = name.substring(name.indexOf(":") + 1);
            System.out.println(n);
        }
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
