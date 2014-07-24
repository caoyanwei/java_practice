package com.kwk.email;

import org.junit.Test;

public class JavaMailTest {
    @Test
    public void basicTest() {
        JavaMail se = new JavaMail(false);
        se.doSendHtmlEmail("邮件主题", "邮件内容", "bbbbbb@163.com");
    }
}
