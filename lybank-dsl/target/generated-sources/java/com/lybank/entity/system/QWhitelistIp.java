package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QWhitelistIp is a Querydsl query type for WhitelistIp
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QWhitelistIp extends EntityPathBase<WhitelistIp> {

    private static final long serialVersionUID = 1886613698L;

    public static final QWhitelistIp whitelistIp = new QWhitelistIp("whitelistIp");

    public final NumberPath<Long> createId = createNumber("createId", Long.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ip = createString("ip");

    public final NumberPath<Integer> isEnable = createNumber("isEnable", Integer.class);

    public final StringPath merchantCode = createString("merchantCode");

    public final NumberPath<Long> operaterId = createNumber("operaterId", Long.class);

    public final DateTimePath<java.util.Date> operaterTime = createDateTime("operaterTime", java.util.Date.class);

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QWhitelistIp(String variable) {
        super(WhitelistIp.class, forVariable(variable));
    }

    public QWhitelistIp(Path<? extends WhitelistIp> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWhitelistIp(PathMetadata metadata) {
        super(WhitelistIp.class, metadata);
    }

}

