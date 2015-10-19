package com.kwk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPasswordField passwordField;
    private JButton loadButton;
    private JButton saveButton;
    private JPanel buttonPanel;
    private JTextArea textArea;

    private String password;


    private Article article;

    public MainFrame(String title) {
        super(title);
        init();
    }

    private void init() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        mainPanel.add(textArea, BorderLayout.CENTER);
        setSize(800, 600);
        setContentPane(mainPanel);
        setVisible(true);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        passwordField = new JPasswordField(12);
        loadButton = new JButton("载入");
        buttonPanel.add(passwordField);
        buttonPanel.add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                password = new String(passwordField.getPassword());

                article = Article.load();

                if (article.checkPassword(password)) {
                    passwordField.setVisible(false);
                    loadButton.setVisible(false);
                    textArea.setText(article.getContent());

                    saveButton = new JButton("保存");
                    saveButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            save();
                        }
                    });
                    buttonPanel.add(saveButton);
                }
            }
        });
    }

    private void save() {
        String content = textArea.getText();
        article.save(content);
    }
}
