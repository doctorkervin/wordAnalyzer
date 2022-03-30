package com.lybank.service;

import com.lybank.entity.scan.TaskInfo;

public class TaskInfoThread extends Thread{
    private TaskInfo taskInfo;

    public TaskInfoThread(TaskInfo taskInfo){
        this.taskInfo = taskInfo;
    }

    public void run(){
        TaskScanner.excute(taskInfo);
    }
}
