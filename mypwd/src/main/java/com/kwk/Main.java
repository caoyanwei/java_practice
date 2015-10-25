package com.kwk;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        UIs.setUI();
        MainFrame frame = new MainFrame("我的文档");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
