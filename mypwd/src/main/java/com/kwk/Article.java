package com.kwk;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

public class Article {
    public static final String PLAIN_HEAD = "asd#123!&*RE);{~bz<|784P";
    public static final String FILE_NAME = "pwd.bin";

    private String head;
    private String content;

    private String realContent;

    private boolean hasCheckPassword = false;
    private String password = "";

    private File file;

    public static Article load() {
        File file = getFile();
        Article article = new Article(file);
        return article;
    }

    private static String getPath() {
        String path = null;
        try {
            path = Article.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException();
        }
        File jarPath = new File(path);
        File file = new File(jarPath.getParent(), FILE_NAME);
        return file.getPath();
    }

    private static File getFile() {
        File file = new File(getPath());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public Article(File file) {
        this.file = file;
        try {
            String all = Files.toString(file, Charsets.UTF_8);
            if (Strings.isNullOrEmpty(all)) {
                init();
                return;
            }
            List<String> strList = Lists.newArrayList(Splitter.on("\r\n").split(all));
            if (strList.size() != 2) {
                init();
                return;
            }
            head = strList.get(0);
            content = strList.get(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        head = "";
        content = "";
    }

    public boolean checkPassword(String password) {
        if (Strings.isNullOrEmpty(head)) {
            setPassword(password);
            return true;
        } else {
            String secretHead = Des.encrypt(Article.PLAIN_HEAD, password);
            if (Objects.equals(secretHead, head)) {
                setPassword(password);
                return true;
            }
        }
        return false;
    }

    private void setPassword(String password) {
        this.password = password;
        hasCheckPassword = true;
        decrypt();
    }

    private void decrypt() {
        if (Strings.isNullOrEmpty(content)) {
            return;
        }

        try {
            realContent = Des.decrypt(content, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getContent() {
        if (!hasCheckPassword) {
            throw new IllegalStateException();
        }

        return realContent;
    }

    public void save(String content) {
        if (!hasCheckPassword) {
            return;
        }
        doSave(content);
    }

    private void doSave(String content) {
        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
            out.write(Des.encrypt(Article.PLAIN_HEAD, password));
            out.write("\r\n");
            out.write(Des.encrypt(content, password));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
