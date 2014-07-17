package com.kwk.jackson;

import com.google.common.base.Objects;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yanwei.cao
 * Date: 14-5-28
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class BasicTest {

    @Test
    public void basicTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InnerAccessLog innerAccessLog = new InnerAccessLog();
        //innerAccessLog.setStaffId("12345");
        innerAccessLog.setUrl("http://locallhost:8080/%asdf%aasdfs");
        Date now = new Date();
        innerAccessLog.setUtcTime(now.getTime());

        String ret = objectMapper.writeValueAsString(innerAccessLog);
        System.out.println(ret);

        InnerAccessLog innerAccessLog2 = objectMapper.readValue(ret, InnerAccessLog.class);
        String ret2 = Objects.toStringHelper(innerAccessLog2).add("staffId", innerAccessLog2.getStaffId()).add("url", innerAccessLog2.getUrl())
                .add("utcTime", innerAccessLog2.getUtcTime()).toString();
        System.out.println(ret2);
    }

    @Test
    public void test2() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> des = new ArrayList<String>();
        des.add("aaaa");
        des.add("bbbb");
        String s = objectMapper.writeValueAsString(des);
        System.out.println(s);
    }
}

class InnerAccessLog {
    private String staffId;
    private String url;
    private long utcTime;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getUtcTime() {
        return utcTime;
    }

    public void setUtcTime(long utcTime) {
        this.utcTime = utcTime;
    }
}
