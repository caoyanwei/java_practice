package com.kwk.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class NotifyMsgTest {

    @Test
    public void basicTest() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        NotifyMsg notifyMsg = new NotifyMsg();
        notifyMsg.setBriefInfo("aaaaa");
        notifyMsg.setDetailUrl("http://aaa.com/abc?clubId=1&dpUserId=${dpUserId}");
        System.out.println(mapper.writeValueAsString(notifyMsg));

    }
}

class NotifyMsg {
    private String briefInfo;
    private String detailUrl;

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
