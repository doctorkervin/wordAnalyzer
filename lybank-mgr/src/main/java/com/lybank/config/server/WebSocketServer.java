package com.lybank.config.server;

import com.lybank.config.MyEndpointConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ServerEndpoint(value = "/log", configurator = MyEndpointConfigure.class)
@Slf4j
@Component
public class WebSocketServer {

    @Value("${spring.application.name}")
    private String applicationName;

    private Process process;
    private InputStream inputStream;

    /**
     * 连接集合
     */
    private static Map<String, Session> sessionMap = new ConcurrentHashMap<String, Session>();
    private static Map<String, Integer> lengthMap = new ConcurrentHashMap<String, Integer>();

    /**
     * 新的WebSocket请求开启
     */
    @OnOpen
    public void onOpen(Session session) {
        /*try {
            StringBuilder sb = new StringBuilder();
            sb.append("tail -f ");
            String filePath = System.getProperty("user.home") + "/log/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"+applicationName+".log";
            sb.append(filePath);
            process = Runtime.getRuntime().exec(sb.toString());
            inputStream = process.getInputStream();
            TailfLogThread thread = new TailfLogThread(inputStream, session);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //添加到集合中
        sessionMap.put(session.getId(), session);
        lengthMap.put(session.getId(), 1);//默认从第一行开始

        //获取日志信息
        new Thread(() -> {
            log.info("LoggingWebSocketServer 任务开始");
            boolean first = true;
            while (sessionMap.get(session.getId()) != null) {
                BufferedReader reader = null;
                try {
                    //日志文件路径，获取最新的
                    String filePath = System.getProperty("user.home") + "/log/" + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/"+applicationName+".log";

                    //字符流
                    reader = new BufferedReader(new FileReader(filePath));
                    Object[] lines = reader.lines().toArray();

                    //只取从上次之后产生的日志
                    Object[] copyOfRange = Arrays.copyOfRange(lines, lengthMap.get(session.getId()), lines.length);

                    //对日志进行着色，更加美观  PS：注意，这里要根据日志生成规则来操作
                    for (int i = 0; i < copyOfRange.length; i++) {
                        String line = (String) copyOfRange[i];
                        //先转义
                        line = line.replaceAll("&", "&amp;")
                                .replaceAll("<", "&lt;")
                                .replaceAll(">", "&gt;")
                                .replaceAll("\"", "&quot;");

                        //处理等级
                        line = line.replace("DEBUG", "<span style='color: blue;'>DEBUG</span>");
                        line = line.replace("INFO", "<span style='color: green;'>INFO</span>");
                        line = line.replace("WARN", "<span style='color: orange;'>WARN</span>");
                        line = line.replace("ERROR", "<span style='color: red;'>ERROR</span>");

                        //处理类名
                        String[] split = line.split("]");
                        if (split.length >= 2) {
                            String[] split1 = split[1].split("-");
                            if (split1.length >= 2) {
                                line = split[0] + "]" + "<span style='color: #298a8a;'>" + split1[0] + "</span>" + "-" + split1[1];
                            }
                        }

                        // 匹配日期开头加换行，2019-08-12 14:15:04
                        Pattern r = Pattern.compile("[\\d+][\\d+][\\d+][\\d+]-[\\d+][\\d+]-[\\d+][\\d+] [\\d+][\\d+]:[\\d+][\\d+]:[\\d+][\\d+]");
                        Matcher m = r.matcher(line);
                        if (m.find( )) {
                            //找到下标
                            int start = m.start();
                            //插入
                            StringBuilder  sb = new StringBuilder (line);
                            sb.insert(start,"<br/><br/>");
                            line = sb.toString();
                        }

                        copyOfRange[i] = line;
                    }

                    //存储最新一行开始
                    lengthMap.put(session.getId(), lines.length);

                    //第一次如果太大，截取最新的200行就够了，避免传输的数据太大
                    if(first && copyOfRange.length > 200){
                        copyOfRange = Arrays.copyOfRange(copyOfRange, copyOfRange.length - 200, copyOfRange.length);
                        first = false;
                    }

                    String result = StringUtils.join(copyOfRange, "<br/>");

                    //发送
                    send(session, result);

                    //休眠一秒
                    Thread.sleep(1000);
                } catch (Exception e) {
                    //捕获但不处理
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException ignored) {
                    }
                }
            }
            log.info("LoggingWebSocketServer 任务结束");
        }).start();
    }

    /**
     * WebSocket请求关闭
     */
    @OnClose
    public void onClose(Session session) {
        //从集合中删除
        sessionMap.remove(session.getId());
        lengthMap.remove(session.getId());
        /*try {
            if(inputStream != null)
                inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(process != null)
            process.destroy();*/
    }

    @OnError
    public void onError(Throwable thr) {
        thr.printStackTrace();
    }

    /**
     * 封装一个send方法，发送消息到前端
     */
    private void send(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



