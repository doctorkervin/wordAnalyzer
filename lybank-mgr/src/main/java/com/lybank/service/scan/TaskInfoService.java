package com.lybank.service.scan;

import com.lybank.entity.scan.QTaskInfo;
import com.lybank.entity.scan.Regular;
import com.lybank.entity.scan.TaskInfo;
import com.lybank.repository.scan.RegularRep;
import com.lybank.repository.scan.TaskInfoRep;
import com.lybank.service.BaseService;
import com.lybank.service.TaskRunThreadService;
import com.lybank.service.TaskScanner;
import com.lybank.util.Result;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskInfoService extends BaseService {

    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.upload-dir}")
    private String upload_dir;
    @Autowired
    private TaskInfoRep taskInfoRep;
    @Autowired
    private RegularRep regularRep;
    @Autowired
    private TaskRunThreadService taskRunThreadService;


    public Page<TaskInfo> getPageList(TaskInfo param, Pageable page) {
        BooleanBuilder b = new BooleanBuilder();
        QTaskInfo q = QTaskInfo.taskInfo;
        if (!StringUtils.isEmpty(param.getFuzzyValue())) {
            b.or(q.name.like("%" + param.getFuzzyValue() + "%"));
        }
        return taskInfoRep.findAll(b, page);
    }


    /**
     *
     * @param r
     * @return
     */
    public Result save(TaskInfo r) {
        try{
            Date date = new Date();
            Long userId = getUserId();
            r.setCreateId(userId);
            r.setOperaterId(userId);
            r.setCreateTime(date);
            r.setOperaterTime(date);
            List<Long> idList = r.getIdList();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < idList.size(); i++) {
                if (i > 0){
                    sb.append(",");
                }
                sb.append(idList.get(i));
            }
            r.setRegularIds(sb.toString());
            r.setStatus(0); //初始化
            if (r.getFileType().equals(TaskScanner.FILE_UPLOAD)){
                r.setFilePath(upload_dir);
            }
            taskInfoRep.save(r);
            return new Result().ResultSuccess();
        }catch (Exception e){
            return new Result().ResultFail();
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public TaskInfo findById(Long id) {
        TaskInfo taskInfo = taskInfoRep.getOne(id);
        List<Long> ids = Arrays.stream(taskInfo.getRegularIds().split(","))
                .map(str->Long.valueOf(str)).collect(Collectors.toList());
        List<Regular> regularList = regularRep.findAllById(ids);
        taskInfo.setRegulars(regularList);
        return taskInfo;
    }

    /**
     * 运行规则方法
     * @param id
     * @return
     */
    public Result run(Long id) {
        TaskInfo taskInfo = taskInfoRep.findById(id).orElse(null);
        taskInfo.setStatus(1);//任务运行中
        String[] ids = taskInfo.getRegularIds().split(",");
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            list.add(Long.valueOf(ids[i]));
        }
        taskInfo.setRegulars(regularRep.findAllById(list));
        try{
            taskInfoRep.saveAndFlush(taskInfo);
            //new TaskInfoThread(taskInfo).run();
            taskRunThreadService.executeAysncTask(taskInfo);
            return new Result().ResultSuccess();
        }catch (Exception e){
            return new Result().ResultFail();
        }
    }

    /**
     * 更新
     * @param task
     * @return
     */
    public Result update(TaskInfo task) {
        TaskInfo taskInfo = taskInfoRep.findById(task.getId()).orElse(null);
        List<Long> idList = task.getIdList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idList.size(); i++) {
            if (i > 0){
                sb.append(",");
            }
            sb.append(idList.get(i));
        }
        taskInfo.setRegularIds(sb.toString());
        taskInfo.setOperaterTime(new Date());
        taskInfoRep.saveAndFlush(taskInfo);
        return new Result().ResultSuccess();
    }
}
