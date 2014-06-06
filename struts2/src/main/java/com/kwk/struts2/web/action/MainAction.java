package com.kwk.struts2.web.action;


import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport {
    private String name;

    public String execute() throws Exception {
        name = "kongkong";
        return SUCCESS;
    }

    public String getName() {
        return name;
    }
}
