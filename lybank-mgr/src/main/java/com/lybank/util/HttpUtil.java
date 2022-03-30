package com.lybank.util;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;


public class HttpUtil {


    /**
     * @param urlParam
     */
    public static String sendPost(String urlParam, Map<String, String> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, String> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        URLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            URL realUrl = new URL(urlParam);
            // 打开和URL之间的连接
            con = realUrl.openConnection();
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            con.setDoOutput(true);
            con.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            if (sbParams != null && sbParams.length() > 0) {
                // 发送请求参数
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                // flush输出流的缓冲
                osw.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }

    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Charset", "utf-8");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
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
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送post 请求
     */
    public static String sendPostM(String urlParam, Map<String, String> params, String charset) {
        StringBuffer resultBuffer = null;
        // 构建请求参数
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Entry<String, String> e : params.entrySet()) {
                sbParams.append(e.getKey());
                sbParams.append("=");
                sbParams.append(e.getValue());
                sbParams.append("&");
            }
        }
        URLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        try {
            URL realUrl = new URL(urlParam);
            // 打开和URL之间的连接
            con = realUrl.openConnection();
            // 设置通用的请求属性
            con.setRequestProperty("accept", "*/*");
            con.setRequestProperty("connection", "Keep-Alive");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            con.setDoOutput(true);
            con.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            osw = new OutputStreamWriter(con.getOutputStream(), charset);
            if (sbParams != null && sbParams.length() > 0) {
                // 发送请求参数
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                // flush输出流的缓冲
                osw.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    osw = null;
                    throw new RuntimeException(e);
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    br = null;
                    throw new RuntimeException(e);
                }
            }
        }
        return resultBuffer.toString();
    }

    /**
     * @param data 格式为key1=value1&key2=value2;
     * @param URL  请求地址
     * @return 返回的JSON字符串
     * @发送POST请求
     */
    public static String httpPOSTMap(String data, String url) {

        //字节输出流
        OutputStreamWriter out = null;
        //输出
//		PrintWriter out = null;
        //输入
        BufferedReader in = null;
        //结果
        String result = null;
        try {
            URL sendUrl = new URL(url);
            //打开与URL之间通信连接
            URLConnection httpURLConnection = sendUrl.openConnection();
            //设置通用的请求参数
            httpURLConnection.setDoInput(true);            // true表示允许获得输入流,读取服务器响应的数据,该属性默认值为true
            httpURLConnection.setDoOutput(true);            // true表示允许获得输出流,向远程服务器发送数据,该属性默认值为false
            httpURLConnection.setUseCaches(false);            // 禁止缓存
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

//			//获取URLConnection对象对应的输出流
//			out = new PrintWriter(httpURLConnection.getOutputStream());
//			//发送请求
//			out.print(data);
//			//flush输出流的缓存
//			out.flush();

            out = new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8");
            //发送请求
            out.write(data);
//			out.println(data);
            //flush输出流的缓存
            out.flush();

            //定义BufferedReader输入流读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));

            result = in.readLine();

            /////////////////////////
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /////////////////////////


        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param data 格式为key1=value1&key2=value2;
     * @param URL  请求地址
     * @return 返回的JSON字符串
     * @发送POST请求
     */
    public static String httpPOST(String data, String url) {

        //字节输出流
        OutputStreamWriter out = null;
        //输出
//		PrintWriter out = null;
        //输入
        BufferedReader in = null;
        //结果
        String result = null;
        try {
            URL sendUrl = new URL(url);
            //打开与URL之间通信连接
            URLConnection httpURLConnection = sendUrl.openConnection();
            //设置通用的请求参数
            httpURLConnection.setDoInput(true);            // true表示允许获得输入流,读取服务器响应的数据,该属性默认值为true
            httpURLConnection.setDoOutput(true);            // true表示允许获得输出流,向远程服务器发送数据,该属性默认值为false
            httpURLConnection.setUseCaches(false);            // 禁止缓存
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

//			//获取URLConnection对象对应的输出流
//			out = new PrintWriter(httpURLConnection.getOutputStream());
//			//发送请求
//			out.print(data);
//			//flush输出流的缓存
//			out.flush();

            out = new OutputStreamWriter(httpURLConnection.getOutputStream(), "utf-8");
            //发送请求
            out.write(data);
//			out.println(data);
            //flush输出流的缓存
            out.flush();

            //定义BufferedReader输入流读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));

            result = in.readLine();

            /////////////////////////
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /////////////////////////


        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param map 请求参数
     * @return String
     * key1=key1&key2=key2
     * @拼接POST请求要发送的字符串
     */
    public static String splicingHttpString(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();

        for (Entry<String, String> entry : map.entrySet()) {
            try {
                sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * @param map 请求参数
     * @return String
     * key1=key1&key2=key2
     * @throws Exception
     * @拼接验签字符串
     */
    public static String splicingString(Map<String, String> map) {
        StringBuffer sb = new StringBuffer();

        for (Entry<String, String> entry : map.entrySet()) {

            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");

        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    /**
     * @param 指定字符串长 length
     * @return 指定的字符串长度
     * @生成指定长度的随机字符串
     */
    public static String generateLenString(int length) {
        char[] cResult = new char[length];
        int[] flag = {0, 0, 0}; // A-Z, a-z, 0-9
        int i = 0;
        while (flag[0] == 0 || flag[1] == 0 || flag[2] == 0 || i < length) {
            i = i % length;
            int f = (int) (Math.random() * 3 % 3);
            if (f == 0)
                cResult[i] = (char) ('A' + Math.random() * 26);
            else if (f == 1)
                cResult[i] = (char) ('a' + Math.random() * 26);
            else
                cResult[i] = (char) ('0' + Math.random() * 10);
            flag[f] = 1;
            i++;
        }
        return new String(cResult);
    }


    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "*/*");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


}
