package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSystemLog is a Querydsl query type for SystemLog
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSystemLog extends EntityPathBase<SystemLog> {

    private static final long serialVersionUID = -982671223L;

    public static final QSystemLog systemLog = new QSystemLog("systemLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath IP = createString("IP");

    public final StringPath loginName = createString("loginName");

    public final StringPath name = createString("name");

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final StringPath type = createString("type");

    public QSystemLog(String variable) {
        super(SystemLog.class, forVariable(variable));
    }

    public QSystemLog(Path<? extends SystemLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSystemLog(PathMetadata metadata) {
        super(SystemLog.class, metadata);
    }

}

