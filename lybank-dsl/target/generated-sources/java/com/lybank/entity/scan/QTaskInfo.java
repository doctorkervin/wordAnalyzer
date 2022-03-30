package com.lybank.entity.scan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTaskInfo is a Querydsl query type for TaskInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTaskInfo extends EntityPathBase<TaskInfo> {

    private static final long serialVersionUID = 1756689L;

    public static final QTaskInfo taskInfo = new QTaskInfo("taskInfo");

    public final NumberPath<Long> createId = createNumber("createId", Long.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final StringPath filePath = createString("filePath");

    public final StringPath fileType = createString("fileType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> operaterId = createNumber("operaterId", Long.class);

    public final DateTimePath<java.util.Date> operaterTime = createDateTime("operaterTime", java.util.Date.class);

    public final StringPath regularIds = createString("regularIds");

    public final StringPath remark = createString("remark");

    public final StringPath result = createString("result");

    public final StringPath scanner = createString("scanner");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public QTaskInfo(String variable) {
        super(TaskInfo.class, forVariable(variable));
    }

    public QTaskInfo(Path<? extends TaskInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTaskInfo(PathMetadata metadata) {
        super(TaskInfo.class, metadata);
    }

}

