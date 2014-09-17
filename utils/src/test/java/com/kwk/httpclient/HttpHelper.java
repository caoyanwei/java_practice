package com.kwk.httpclient;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import org.apache.http.*;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpHelper.class);

    public static final String UTF_8 = "UTF-8";

    public String get(String url) throws IOException {
        CloseableHttpClient httpClient = genHttpClient();
        ResponseHandler<String> responseHandler = genResponseHandler();
        HttpGet httpget = new HttpGet(url);

        String ret = null;
        try {
            logger.info("get url:{}", url);
            ret = httpClient.execute(httpget, responseHandler);
            logger.info("get ret:{}", ret);
        } finally {
            httpClient.close();
        }

        return ret;
    }

    public String post(String url, Map<String, String> paras) throws IOException {
        CloseableHttpClient httpClient = genHttpClient();
        ResponseHandler<String> responseHandler = genResponseHandler();

        HttpPost httpPost = getHttpPost(url, paras);

        String ret = null;
        try {
            logger.info("post url:{}", url);
            String paraStr = "";
            if (paras != null) {
                paraStr = Joiner.on(',').useForNull("").withKeyValueSeparator(":").join(paras);
            }
            logger.info("post paras:{}", paraStr);
            ret = doExecute(httpClient, httpPost, responseHandler);
            logger.info("post ret:{}", ret);
        } finally {
            httpClient.close();
        }

        return ret;
    }

    private HttpPost getHttpPost(String url, Map<String, String> paras) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity entity = genRequestEntity(paras);
        httpPost.setEntity(entity);
        return httpPost;
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).setConnectionRequestTimeout(3000).build();
    }

    private UrlEncodedFormEntity genRequestEntity(Map<String, String> paras) throws UnsupportedEncodingException {
        List<NameValuePair> formParams = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : paras.entrySet()) {
            if (Strings.isNullOrEmpty(entry.getValue())) {
                continue;
            }
            formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        UrlEncodedFormEntity entity = null;
        entity = new UrlEncodedFormEntity(formParams, Charsets.UTF_8);
        return entity;
    }

    private ResponseHandler<String> genResponseHandler() {
        return new ResponseHandler<String>() {
            public String handleResponse(
                    final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();

                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toString(entity, UTF_8);
                }

                return null;
            }
        };
    }

    private String doExecute(CloseableHttpClient httpClient, HttpPost httpPost, ResponseHandler<String> responseHandler) {
        try {
            String ret = httpClient.execute(httpPost, responseHandler);
            return ret;
        } catch (IOException e) {
            logger.error("failed to httpClient.execute", e);
            return null;
        }
    }

    private CloseableHttpClient genHttpClient() {
        RequestConfig requestConfig = getRequestConfig();
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).addInterceptorFirst(new HttpResponseInterceptor() {
            @Override
            public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    Header contentEncoding = entity.getContentEncoding();
                    if (contentEncoding != null) {
                        HeaderElement[] headerElements = contentEncoding.getElements();
                        for (HeaderElement headerElement : headerElements) {
                            if (headerElement.getName().equalsIgnoreCase("gzip")) {
                                response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }
        }).setRedirectStrategy(new DefaultRedirectStrategy() {
            public boolean isRedirected(HttpRequest request, HttpResponse response, HttpContext context) {
                boolean isRedirect = false;
                try {
                    isRedirect = super.isRedirected(request, response, context);
                } catch (ProtocolException e) {
                    logger.error("ProtocolException. ", e);
                }
                if (!isRedirect) {
                    int responseCode = response.getStatusLine().getStatusCode();
                    if (responseCode == 301 || responseCode == 302) {
                        return true;
                    }
                }
                return isRedirect;
            }
        }).build();
    }
}
