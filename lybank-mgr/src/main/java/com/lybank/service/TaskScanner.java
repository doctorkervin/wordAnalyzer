package com.lybank.service;

import com.lybank.entity.scan.Regular;
import com.lybank.entity.scan.TaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskScanner {
    public static final String FILE_UPLOAD = "1"; //上传文件
    private final String FILE_DEFINE = "2"; //自定义文件路径
    private static List<File> fileList = new ArrayList<>();

    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private static final Logger logger = LoggerFactory.getLogger(TaskScanner.class);
    /**
     * 解析任务
     * @param taskInfo
     */
    public static void excute(TaskInfo taskInfo) {
        String filePath = taskInfo.getFilePath();
        try {
            //读取文件
            readfile(filePath);
            //解析文件
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                scan(file,taskInfo);
            }
            //notify update db

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {
            File file = new File(filepath);
            if (!file.isDirectory()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "/" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        fileList.add(readfile);
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "/" + filelist[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.error("readfile()   Exception:"+e.getMessage());
        }
        return true;
    }

    private static void scan(File file , TaskInfo taskInfo){
        List<Regular> regulars = taskInfo.getRegulars();
        logger.info("扫描文件开始，文件名称："+file.getName() + "开始");

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            for (int i = 0; i < regulars.size(); i++) {
                Regular regular1 = regulars.get(i);
                logger.info("扫描规则名："+regular1.getName() + "  开始");
                String regular = regular1.getValue();
                Pattern p = Pattern.compile(regular);
                br.lines().forEach(s -> {
                    Matcher m = p.matcher(s);
                    while(m.find()){
                        logger.info("匹配该规则："+m.group(0));
                    }
                });
                logger.info("扫描规则名："+regular1.getName() + "  结束");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();//只是测试用例，生产环境下不要这样做异常处理
        }
        logger.info("扫描文件，文件名称："+file.getName() + "结束");
    }
}
