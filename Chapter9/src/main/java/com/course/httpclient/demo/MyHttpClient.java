package com.course.httpclient.demo;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {


@Test
    public void test1() throws IOException {
      String result;
        HttpGet httpGet = new HttpGet("https://www.baidu.com");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpGet);
        result = EntityUtils.toString(response.getEntity(),"UTF-8");
        System.out.println(result);



    }

}
