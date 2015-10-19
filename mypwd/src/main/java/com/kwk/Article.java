package com.kwk;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Article {
    public static final String PLAIN_HEAD = "asd#123!&*RE);{~bz<|784P";
    public static final String DEFAULT_PATH = "C:/a.bin";

    private String head;
    private String content;

    private String realHead;
    private String realContent;

    private boolean hasCheckPassword = false;
    private String password = "";

    private File file;

    public static Article load() {
        File file = getFile();
        Article article = new Article(file);
        return article;
    }

    private static File getFile() {
        File file = new File(DEFAULT_PATH);
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
        // TODO
    }

    public String getContent() {
        if (!hasCheckPassword) {
            throw new IllegalStateException();
        }

        return realContent;
    }

    public void save(String content) {
        if (!hasCheckPassword) {
            throw new IllegalStateException();
        }
        doSave(content);
    }

    private void doSave(String content) {
        // TODO
    }

}
