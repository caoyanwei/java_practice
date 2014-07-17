package com.kwk.httpclient;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeLifeTest {
    @Test
    public void test1() throws Exception {
        CloseableHttpClient httpClient = HttpClients.custom().addInterceptorFirst(new HttpResponseInterceptor() {

            @Override
            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header ceheader = entity.getContentEncoding();
                    if (ceheader != null) {
                        HeaderElement[] codecs = ceheader.getElements();
                        for (int i = 0; i < codecs.length; i++) {
                            if (codecs[i].getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(
                                        new GzipDecompressingEntity(response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }
        }).build();

        List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair("client", "dpcard"));
        formParams.add(new BasicNameValuePair("", ""));
        formParams.add(new BasicNameValuePair("", ""));
        formParams.add(new BasicNameValuePair("", ""));
        formParams.add(new BasicNameValuePair("", ""));
        formParams.add(new BasicNameValuePair("", ""));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams);

        HttpPost httpPost = new HttpPost("http://open.life.pre59.dev.tencentwsh.com/dpcard.php");
        //HttpPost httpPost = new HttpPost("http://www.apache.org/");
        httpPost.setEntity(entity);


        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                HttpEntity entity = response.getEntity();
                //System.out.println(EntityUtils.toString(entity));
                return EntityUtils.toString(entity, "UTF-8");
            }
        };

        try {
            String ret = httpClient.execute(httpPost, responseHandler);
            System.out.println(ret);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
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
