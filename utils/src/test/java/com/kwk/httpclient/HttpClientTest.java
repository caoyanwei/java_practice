package com.kwk.httpclient;


import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpClientTest {

    @Test
    public void basicTest() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("jsonData", getData()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");

        //HttpPost httpPost = new HttpPost("http://localhost:8080/vipapi/welife/addcard?systemId=10001&ts=1404704453&sign=27ab81fc4bfa8089eb3a95f8bfbac6d3");
        HttpPost httpPost = new HttpPost("http://192.168.217.234/vipapi/welife/addcard");
        httpPost.setEntity(entity);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity));
                return EntityUtils.toString(entity);
//                int status = response.getStatusLine().getStatusCode();
//                if (status >= 200 && status < 300) {
//                    HttpEntity entity = response.getEntity();
//                    return entity != null ? EntityUtils.toString(entity) : null;
//                } else {
//                    throw new ClientProtocolException("Unexpected response status: " + status);
//                }
            }

        };

        try {
            httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        finally {
            httpClient.close();
        }

        System.out.println("exit");
    }

    private String getData() throws Exception {
        URL url = getClass().getResource("/json/cardInfo.json");
        File f = new File(toURI(url.toString()).getSchemeSpecificPart());
        String jsonData = Files.toString(f, Charsets.UTF_8);

        String key = "fa38f*68d@b9c#832b";

        String data = CryptUtil.encrypt(jsonData, key);
        return data;
    }

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(location);
    }
}
