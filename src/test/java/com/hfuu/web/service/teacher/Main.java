package com.hfuu.web.service.teacher;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
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
    public void test() throws IllegalAccessException {
        Main m = new Main();
        List<String> li = new ArrayList<>();
        li.add("zs");
        li.add("ls");
        m.setId(12);
        m.setName("whh");
        m.setFriends(li);

        JSONObject jsonObject = new JSONObject();
        String json = JSONObject.toJSONString(m);
        JSONObject js = JSONObject.parseObject(json);
        System.out.println(json);
        System.out.println(js);
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