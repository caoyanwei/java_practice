package com.kwk.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yanwei.cao
 * Date: 14-5-26
 * Time: 下午5:35
 * To change this template use File | Settings | File Templates.
 */
public class BasicTest {

    @Test
    public void basicTest() throws IOException, TemplateException {
        Configuration cfg = new Configuration();

        cfg.setDefaultEncoding("utf-8");
        cfg.setClassicCompatible(false);
        cfg.setOutputEncoding("utf-8");

        cfg.setTemplateExceptionHandler(new TemplateExceptionHandler() {
            @Override
            public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
                System.out.println(te);
            }
        });

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        cfg.setClassForTemplateLoading(BasicTest.class, "/freemarker");

        Template template = cfg.getTemplate("basic.ftl");

        Map<String, String> root = new HashMap<String, String>();
        root.put("name", "中国");
        Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);
    }
}
