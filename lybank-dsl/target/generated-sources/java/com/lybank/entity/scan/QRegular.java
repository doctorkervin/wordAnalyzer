package com.lybank.entity.scan;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRegular is a Querydsl query type for Regular
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegular extends EntityPathBase<Regular> {

    private static final long serialVersionUID = 684119454L;

    public static final QRegular regular = new QRegular("regular");

    public final StringPath code = createString("code");

    public final NumberPath<Long> createId = createNumber("createId", Long.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> operaterId = createNumber("operaterId", Long.class);

    public final DateTimePath<java.util.Date> operaterTime = createDateTime("operaterTime", java.util.Date.class);

    public final StringPath remark = createString("remark");

    public final StringPath type = createString("type");

    public final StringPath value = createString("value");

    public QRegular(String variable) {
        super(Regular.class, forVariable(variable));
    }

    public QRegular(Path<? extends Regular> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRegular(PathMetadata metadata) {
        super(Regular.class, metadata);
    }

}

