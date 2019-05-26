package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by 19168 on 2019-05-25.
 */
public class MyCookiesForPost {

    private String url;
    private ResourceBundle bundle;
    //用来存储cookies信息的变量
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException{

        String url=bundle.getString("getCookies.uri");
        String testUrl=this.url+url;
        this.store = new BasicCookieStore();
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        List<Cookie> cookieList = store.getCookies();

        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name = " + name
                    + ";  cookie value = " + value);
        }

    }
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPOSTWithCookies() throws IOException{
        String uri = bundle.getString("test.psot.with.cookies");
        String testUrl = this.url+uri;
        HttpPost post = new HttpPost(testUrl);
        CloseableHttpClient client = HttpClients.createDefault();
        //创建请求背景
        HttpClientContext context = HttpClientContext.create();
        //创建JSON参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex","18");
        jsonObject.put("name","taopeipei");
        post.setHeader("content-type", "application/json");
        //设置POST参数
        StringEntity entity = new StringEntity(jsonObject.toString(),"UTF-8");
        post.setEntity(entity);
        //设置COOKIES
        context.setCookieStore(this.store);
        HttpResponse reponse = client.execute(post,context);
        //获取返回值
        String result= EntityUtils.toString(reponse.getEntity());
        int statusCode = reponse.getStatusLine().getStatusCode();
        System.out.println("statusCode = " + statusCode);
        if(statusCode == 200){
           JSONObject resultjson = new JSONObject(result);
            String success = (String)resultjson.get("huhansan");
            String status = (String)resultjson.get("status");
            Assert.assertEquals("success",success);
        }



    }


}
