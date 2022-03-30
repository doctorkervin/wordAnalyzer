package com.lybank.service;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.lybank.entity.scan.Regular;
import com.lybank.entity.scan.TaskInfo;
import com.lybank.repository.scan.TaskInfoRep;
import com.lybank.util.Constant;
import com.lybank.util.Validation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TaskRunThreadService {
    private Logger logger = LoggerFactory.getLogger(TaskRunThreadService.class);

    private  List<File> fileList = new ArrayList<>();
    private StringBuilder sb = new StringBuilder();
    private StringBuilder sb2 = new StringBuilder();
    @Autowired
    private TaskInfoRep taskInfoRep;

    @Async
    public void executeAysncTask(TaskInfo taskInfo){
        String filePath = taskInfo.getFilePath();

        try {
            //读取文件
            readfile(filePath);
            //解析文件
            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);
                scan(file,taskInfo);
            }
            if (Constant.Task_Secret.equals(taskInfo.getFileType())){
                logger.info(sb.toString());
            }
            //notify update db
            taskInfo.setStatus(2);//任务完成
            Date date = new Date();
            taskInfo.setOperaterTime(date);
            taskInfo.setResult(sb.toString());//扫描结果
            taskInfoRep.saveAndFlush(taskInfo);
            sb.setLength(0);//清空sb
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取某个文件夹下的所有文件
     */
    private boolean readfile(String filepath) throws FileNotFoundException, IOException {
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

    /**
     * 扫描文件
     * @param file
     * @param taskInfo
     */
    private  void scan(File file , TaskInfo taskInfo){
        List<Regular> regulars = taskInfo.getRegulars();
        if (taskInfo.getFileType().equals(Constant.Task_Secret)){
            int i = Constant.Secret_File_Extension.indexOf(getFileExtension(file));
            if (i>-1){
                sb.append("发现密钥文件：").append(file.getAbsolutePath());
            }
            return;
        }
        logger.info("扫描文件开始，文件名称："+file.getName()+"-----------");
        //sb.append("扫描文件开始，文件名称：").append(file.getName()).append(">>>>> 开始");
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            br.mark( ( int )file.length() + 1 );////在首行做个标记
            for (int i = 0; i < regulars.size(); i++) {
                Regular regular1 = regulars.get(i);
                if (regular1.getType().equals(Constant.Regular_Regex)){
                    runRegrex(br, regular1);
                }else if (Constant.Regular_Nature.equals(regular1.getType())){
                    //自然语言
                    runNature(br,regular1);
                }else {
                    //模糊匹配
                    runFuzzle(br,regular1);
                }

                if (sb2.length()>0){
                    logger.info("扫描规则，规则名称："+regular1.getName()+">>>>>>>>结果："+sb2.toString());
                    sb.append(">>>>>>>>>>扫描规则名：").append(regular1.getName())
                            .append(" 结果：")
                            .append(sb2.toString());
                    sb2.setLength(0);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //logger.info(sb.toString());
    }

    /**
     * 模糊匹配
     * @param br
     * @param regular1
     */
    private void runFuzzle(BufferedReader br, Regular regular1) throws IOException {
        //String[] fuzzleList = regular1.getValue().split("|");
        String fuzzle = regular1.getValue();
        if (StringUtils.isEmpty(fuzzle)){
            throw new RuntimeException("fuzzle must not be null!");
        }
        sb2.append("模糊匹配").append(fuzzle).append(":");
        br.lines().forEach(s -> {
            if (s.contains(fuzzle)){
                sb2.append(s).append(" ");
            }
        });
        br.reset(); //重新定位到流的首位
    }

    /**
     * 自然语言处理
     * @param br
     * @param regular1
     * @throws IOException
     */
    private void runNature(BufferedReader br, Regular regular1) throws IOException {
        String natureValue = regular1.getValue();
        Segment segment;
        if (Constant.Nature_Place.equals(natureValue)){
            segment = HanLP.newSegment().enablePlaceRecognize(true);
        }else if (Constant.Nature_ChineseName.equals(natureValue)){
            segment = HanLP.newSegment().enableNameRecognize(true);
        }else {
            //机构
            segment = HanLP.newSegment().enableOrganizationRecognize(true);
        }
        br.lines().forEach(s -> {
            List<Term> termList = segment.seg(s);
            for (int i = 0; i < termList.size(); i++) {
                Term term = termList.get(i);
                if (term.nature.startsWith(natureValue)){
                    sb2.append(term.word).append(" ");
                }
            }
        });
        br.reset(); //重新定位到流的首位
    }

    /**
     * 正则表达式处理
     * @param br
     * @param regular1
     * @throws IOException
     */
    private void runRegrex(BufferedReader br, Regular regular1) throws IOException {
        String regular;
        regular= Validation.map.get(regular1.getName());
        if (StringUtils.isEmpty(regular)){
            regular = regular1.getValue();
        }
        Pattern p = Pattern.compile(regular);
        br.lines().forEach(s -> {
            Matcher m = p.matcher(s);
            while(m.find()){
                sb2.append(m.group(0)).append(" ");
            }
        });
        br.reset(); //重新定位到流的首位
    }

    private  String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

}
