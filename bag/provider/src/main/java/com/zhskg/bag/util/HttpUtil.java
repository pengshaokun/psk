package com.zhskg.bag.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;

public final class HttpUtil 
{
    private static final int TIMEOUT_IN_MILLIONS = 4000;

	public static String postData(String url,JSONObject object) throws Exception
    {
        try {
            HttpPost httpPost = new HttpPost("http://www.zswwlj.com/api/push/pushData");
            CloseableHttpClient client = HttpClients.createDefault();
            StringEntity entity = new StringEntity(object.toString(), "utf-8");//解决中文乱码问题
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                InputStream stream = resp.getEntity().getContent();
                String[] strings = readLines(stream);
                StringBuffer buffer = new StringBuffer();
                for (String str : strings){
                    buffer.append(str);
                }
                return buffer.toString();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
	
	public static String getData(String url)
    {
        try {
            HttpGet httpPost = new HttpGet(url);
            CloseableHttpClient client = HttpClients.createDefault();
            HttpResponse resp = client.execute(httpPost);
            if (resp.getStatusLine().getStatusCode() == 200) {
                InputStream stream = resp.getEntity().getContent();
                String[] strings = readLines(stream);
                StringBuffer buffer = new StringBuffer();
                for (String str : strings){
                    buffer.append(str);
                }
                return buffer.toString();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
	
	public static String[] readLines(InputStream is) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = reader.readLine()) != null)
                lines.add(line);
            return lines.toArray(new String[0]);
        } finally {
            reader.close();
        }
    }

    public static String doPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(TIMEOUT_IN_MILLIONS);
            conn.setConnectTimeout(TIMEOUT_IN_MILLIONS);

            if (param != null && !"".equals(param.trim())) {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {

        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {

            }
        }
        return result.toString();
    }
}
