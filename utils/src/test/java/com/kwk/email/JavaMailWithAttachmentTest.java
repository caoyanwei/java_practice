package com.kwk.email;

import org.junit.Test;

import java.io.File;

public class JavaMailWithAttachmentTest {
    @Test
    public void basicTest() {
        JavaMailWithAttachment se = new JavaMailWithAttachment(true);
        File affix = new File("c:\\id.jpg");
        se.doSendHtmlEmail("邮件主题", "邮件内容", "aaaaaaaaa@163.com", affix);//
    }
}
