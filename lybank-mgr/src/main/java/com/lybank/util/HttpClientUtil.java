package com.lybank.util;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;


public class HttpClientUtil {

    // 发送次数
    public int count = 0;

    /**
     * @param @param  url String
     * @param @param  paramContent String 拼装好的参数串
     * @param @param  charSet string
     * @param @param  connTimeOut int
     * @param @param  SoTimeOut int
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: submitPost
     * @Description: java.net实现 POST方法提交
     */
    public static String submitPost(String url, String paramContent, String charSet, int connTimeOut, int soTimeOut) {
        StringBuffer message = null;
        URLConnection connection = null;
        URL reqUrl = null;
        OutputStreamWriter reqOut = null;
        InputStream in = null;
        BufferedReader br = null;
        String param = paramContent;
        String identifier = System.currentTimeMillis() + "";
        if (null == charSet || "".equals(charSet)) {
            charSet = "GBK";
        }
        try {
            message = new StringBuffer();//创建对象接收响应信息
            reqUrl = new URL(url);
            connection = reqUrl.openConnection();//创建连接
            connection.setReadTimeout(soTimeOut);//设置读取数据超时时间
            connection.setConnectTimeout(connTimeOut);//设置连接地址超时时间
            connection.setDoOutput(true);//使用post方式的时候，需要使用 URL 连接进行输出，所以设定为true
            reqOut = new OutputStreamWriter(connection.getOutputStream(), charSet);
            reqOut.write(param);//将参数信息添加到输出流中
            reqOut.flush();//刷新该流的缓冲
            int charCount = -1;
            in = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in, charSet));//设定收取响应字符的编码格式
            while ((charCount = br.read()) != -1) {
                message.append((char) charCount);//将响应信息添加到message对象中
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "EXCEPTION";
        } finally {
            try {
                //关闭打开的输入流和输出流
                in.close();
                reqOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return message.toString();
    }

    /**
     * @param @param  url String
     * @param @param  paramContent String 拼装好的参数串
     * @param @param  charSet string
     * @param @param  connTimeOut int
     * @param @param  SoTimeOut int
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: submitPost
     * @Description: java.net实现 POST方法提交
     */

    public static String submitPost(String url, String paramContent, String charSet, String connTimeOutStr, String soTimeOutStr) {
        int connTimeOut = 0;
        int soTimeOut = 0;
        if (null != connTimeOutStr && (!"".equals(connTimeOutStr))) {
            connTimeOut = Integer.parseInt(connTimeOutStr);
        }
        if (null != soTimeOutStr && (!"".equals(soTimeOutStr))) {
            soTimeOut = Integer.parseInt(soTimeOutStr);
        }
        return submitPost(url, paramContent, charSet, connTimeOut, soTimeOut);
    }

    /**
     * @param url     请求地址
     * @param jsonStr json字符串
     * @param charset 字符编码
     * @return
     */
    public static String jsonPost(String url, String jsonStr, String charset) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpPost post = new HttpPost(url);
        String result = null;
        try {
	/*		StringEntity entity = new StringEntity(jsonStr,charset);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json;charset=UTF-8");
			post.setEntity(entity);
			post.setHeader("Content-Type", "application/json");
			response = httpclient.execute(post);*/
            StringEntity entityParams = new StringEntity(jsonStr, charset);
            entityParams.setContentEncoding("UTF-8");
            entityParams.setContentType("application/json");
            post.setEntity(entityParams);
            post.setHeader("Content-Type", "application/json");
            response = httpclient.execute(post);
            HttpEntity he = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(he, charset);
            }
            // 释放资源
            EntityUtils.consume(he);

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            // 释放资源，手动消耗掉response或者取消连接（使用CloseableHttpResponse类的close方法）
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url         请求url
     * @param requestData 请求数据
     * @param method      请求方法，get post
     * @param charset     编码方式 utf-8
     * @return
     */
    public static String submitJSONPostRequest(String url, String requestData, String charset) {
        //String req_msg  =   requestData;
        String req_msg = "";


        StringBuffer buffer = new StringBuffer();
        try {
            req_msg = new String(requestData.getBytes("UTF-8"), "UTF-8");

//				String requestUrl = url;
//				log.info("URL-->>" + requestUrl);
            URL requestUrl = new URL(url);
            URLConnection connection = requestUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) connection;
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type",
                    "application/json;");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            //out.writeUTF(req_msg);
            out.writeBytes(req_msg);

            DataInputStream inputStream = new DataInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, charset));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
//				log.info("HTTP返回-->>" + buffer.toString());
            bufferedReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();

    }

    /**
     * @param url
     * @param requestData
     * @param method
     * @param charset
     * @return
     */
    public static String submitRequest(String url, String requestData, String method, String charset) {
        String req_msg = requestData;

        StringBuffer buffer = new StringBuffer();
        try {
//				String requestUrl = url;
//				log.info("URL-->>" + requestUrl);
            URL requestUrl = new URL(url);
            URLConnection connection = requestUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) connection;
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded;charset=" + charset);
            conn.setRequestMethod(method);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(req_msg);

            DataInputStream inputStream = new DataInputStream(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, charset));

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
//				log.info("HTTP返回-->>" + buffer.toString());
            bufferedReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();

    }

    /**
     * 请求数据流转成字符串
     *
     * @param is
     * @param charset
     * @return
     */
    public static String InputStreamToString(InputStream is, String charset) {
        BufferedReader reader = null;
        StringBuffer responseText = null;
        String readerText = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, charset));
            responseText = new StringBuffer();
            readerText = reader.readLine();
            while (readerText != null) {
                responseText.append(readerText);
                responseText.append(System.getProperty("line.separator"));
                readerText = reader.readLine();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseText.toString();
    }


    /**
     *
     * doPost:(方法的作用：). <br/>
     * TODO(注意事项 ：).<br/>
     * @param url
     * @param str
     * @param charset UTF-8
     * @param contentType  "application/x-www-form-urlencoded"  "text/plain" application/json
     * @return
     *
     * @author lishaofeng  396191970@qq.com
     * @date: 2017年3月1日 上午11:38:09 <br/>
     */
	/*public static  String doPost(String url,String str,String charset,String contentType){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
           StringEntity entityParams = new StringEntity(str, charset);
			httpPost.setEntity(entityParams );
			httpPost.setHeader("Content-Type", contentType);


			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
			    System.out.println("网络状态："+response.getStatusLine().getStatusCode());
				HttpEntity resEntity = response.getEntity();
				if(resEntity != null){
					result = EntityUtils.toString(resEntity,charset);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
*/
	/*public static  HttpEntity doPostEntity(String url,String str,String charset,String contentType){
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		HttpEntity resEntity= null;
		try{
			httpClient = new SSLClient();
			httpPost = new HttpPost(url);
			//设置参数
           StringEntity entityParams = new StringEntity(str, charset);
			httpPost.setEntity(entityParams );
			httpPost.setHeader("Content-Type", contentType);


			HttpResponse response = httpClient.execute(httpPost);
			if(response != null){
				 System.out.println("网络状态："+response.getStatusLine().getStatusCode());
				 resEntity = response.getEntity();

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resEntity;
	}*/
    /**
     * 发送get请求
     *
     * @param url
     *            路径
     * @return
     */
  /* public static JSONObject httpGet1(String url) {
       // get请求返回结果
       JSONObject jsonResult = null;
       try {
           CloseableHttpClient client = HttpClientBuilder.create().build();
           // 发送get请求
           HttpGet request = new HttpGet(url);
           HttpResponse response = client.execute(request);

           *//** 请求发送成功，并得到响应 **//*
           if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
               *//** 读取服务器返回过来的json字符串数据 **//*
               String strResult = EntityUtils.toString(response.getEntity());
               *//** 把json字符串转换成json对象 **//*
               jsonResult = JSONObject.parseObject(strResult);
               url = URLDecoder.decode(url, "UTF-8");
           } else {
           }
       } catch (IOException e) {
       }
       return jsonResult;
   }
*/

    /**
     * @param url       String 发送地址
     * @param charSet   String 字符设置
     * @param timeOut   int 连接超时 毫秒
     * @param SoTimeout int 读写超时时间 毫秒
     * @return String 返回类型 FAIL 处理失败， EXCEPTION 系统异常 ，其他的为response结果
     * @throws
     * @Title: httpGetMethod
     * @Description: HTTP GET 发送方式
     */
    public String httpGet(String url, String charSet, int connTimeOut, int soTimeOut) {

        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        // 创建GET方法的实例
        GetMethod getMethod = new GetMethod(url);

        // 设置连接超时与读写超时时间
        if (connTimeOut != 0)
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeOut);
        if (soTimeOut != 0)
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeOut);

        // 设置字符编码
        charSet = null == charSet ? "GBK" : charSet;
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

        // 使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        try {
            // 执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                byte[] responseBody = getMethod.getResponseBody();

                return new String(responseBody, charSet);
            }
            return "FAIL";
        } catch (HttpException e) {
            // 发生致命的异常，可能是协议不对或者返回的内容有问题
            e.printStackTrace();
            return "EXCEPTION";
        } catch (IOException e) {
            // 发生网络异常
            e.printStackTrace();
            return "EXCEPTION";
        } finally {
            // 释放连接
            getMethod.releaseConnection();
        }
    }

    /**
     * @param url       String 发送地址
     * @param charSet   String 字符设置
     * @param timeOut   String 连接超时 毫秒
     * @param SoTimeout String 读写超时时间 毫秒
     * @return String 返回类型 FAIL 处理失败， EXCEPTION 系统异常 ，其他的为response结果
     * @throws
     * @Title: httpGetMethod
     * @Description: HTTP GET 发送方式
     */
    public String httpGet(String url, String charSet, String connTimeOutStr, String soTimeOutStr) {

        int connTimeOut = 0;
        int soTimeOut = 0;
        if (null != connTimeOutStr && (!"".equals(connTimeOutStr))) {
            connTimeOut = Integer.parseInt(connTimeOutStr);
        }
        if (null != soTimeOutStr && (!"".equals(soTimeOutStr))) {
            soTimeOut = Integer.parseInt(soTimeOutStr);
        }
        return httpGet(url, charSet, connTimeOut, soTimeOut);
    }

    /**
     * @param url       String
     *                  发送地址
     * @param nameValue String
     *                  Map 发送数据
     * @param charSet   String
     *                  字符设置
     * @param timeOut   int
     *                  连接超时 毫秒
     * @param SoTimeout int
     *                  读写超时时间 毫秒
     * @return String 返回类型 FAIL 处理失败， EXCEPTION 系统异常 ，其他的为response结果
     * @throws
     * @Title: httpPost
     * @Description: HTTP POST 发送方式
     */
    public String httpPost(String url, Map<String, String> nameValue, String charSet, int connTimeOut, int soTimeOut) {

        // 构造HttpClient的实例
        HttpClient httpClient = new HttpClient();

        PostMethod postMethod = new PostMethod(url);

        // 超时时间
        if (connTimeOut != 0)
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(connTimeOut);
        if (soTimeOut != 0)
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeOut);

        // 填入各个表单域的值
        if (null != nameValue) {
            Set<String> keys = nameValue.keySet();
            for (String key : keys) {
                postMethod.setParameter(key, nameValue.get(key));
            }
        }

        // 设置字符编码
        charSet = null == charSet ? "GBK" : charSet;
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

        try {

            // 执行postMethod
            int statusCode = httpClient.executeMethod(postMethod);

            // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
            // 301或者302
            if (statusCode == HttpStatus.SC_OK) {
                byte[] responseBody = postMethod.getResponseBody();
                charSet = null != charSet ? charSet : "GBK";
                return new String(responseBody, charSet);
            } else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {

                // 需要自己处理转发
                // 从头中取出转向的地址
                Header locationHeader = postMethod.getResponseHeader("location");
                if (locationHeader != null) {
                    String location = locationHeader.getValue();
                    if (count == 0) {// 只运行转发向一次，防止死循环
                        count++;
                        return httpPost(location, nameValue, charSet, connTimeOut, soTimeOut);
                    } else
                        return "FAIL";
                } else {
                    return "FAIL";
                }
            } else {
                return "FAIL";
            }
        } catch (HttpException e) {
            e.printStackTrace();
            return "EXCEPTION";
        } catch (IOException e) {
            e.printStackTrace();
            return "EXCEPTION";
        } finally {
            postMethod.releaseConnection();
        }
    }

    /**
     * @param url          发送地址
     * @param nameValue    Map 发送数据
     * @param charSet      字符设置
     * @param timeOutStr   连接超时 毫秒
     * @param SoTimeoutStr 读写超时时间 毫秒
     * @return String 返回类型 FAIL 处理失败， EXCEPTION 系统异常 ，其他的为response结果
     * @throws
     * @Title: httpPost
     * @Description: HTTP POST 发送方式
     */
    public String httpPost(String url, Map<String, String> nameValue, String charSet, String connTimeOutStr, String soTimeOutStr) {

        int connTimeOut = 0;
        int soTimeOut = 0;
        if (null != connTimeOutStr && (!"".equals(connTimeOutStr))) {
            connTimeOut = Integer.parseInt(connTimeOutStr);
        }
        if (null != soTimeOutStr && (!"".equals(soTimeOutStr))) {
            soTimeOut = Integer.parseInt(soTimeOutStr);
        }
        return httpPost(url, nameValue, charSet, connTimeOut, soTimeOut);
    }
}
