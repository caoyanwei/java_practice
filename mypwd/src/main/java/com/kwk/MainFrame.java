package com.kwk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        JPanel mainPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        int width = 800;
        int height = 600;
        setSize(width, height);
        setLocation((screenWidth - width) / 2, (screenHeight - height) / 2);

        setContentPane(mainPanel);
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        passwordField = new JPasswordField(12);
        loadButton = new JButton("载入");
        buttonPanel.add(passwordField);
        buttonPanel.add(loadButton);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    load();
                }
            }
        });

        InputMap inputMap = textArea.getInputMap();
        ActionMap actionMap = textArea.getActionMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), "insertPwd");
        actionMap.put("insertPwd", new AbstractAction("") {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertPwd();
            }
        });

        setVisible(true);
    }

    private void insertPwd() {
        textArea.insert(PwdHelper.getPwd(12), textArea.getCaretPosition());
    }

    private void load() {
        password = new String(passwordField.getPassword());

        article = Article.load();

        if (article.checkPassword(password)) {
            textArea.setEditable(true);
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
        } else {
            passwordField.setText("");
        }
    }

    private void exit() {
        save();
        dispose();
        System.exit(0);
    }

    private void save() {
        if (article != null) {
            String content = textArea.getText();
            article.save(content);
        }
    }
}
