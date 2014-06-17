package com.kwk.struts2.web.action;

import com.opensymphony.xwork2.ActionSupport;

public class JsonBasicAction extends ActionSupport {
    private String name;
    private int age;

    public String execute() throws Exception {
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
