package com.kwk.url;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created with IntelliJ IDEA.
 * User: yanwei.cao
 * Date: 14-5-28
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public class BasicTest {

    @Test
    public void basicTest() throws UnsupportedEncodingException {
        String encodeStr = URLEncoder.encode("中国", "utf-8");
        System.out.println(encodeStr);
        String originStr = URLDecoder.decode("q=2&name=%E4%B8%AD%E5%9B%BD#third", "utf-8");


    }
}
