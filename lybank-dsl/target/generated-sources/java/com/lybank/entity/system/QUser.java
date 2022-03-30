package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -258688681L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> accountExpired = createNumber("accountExpired", Integer.class);

    public final NumberPath<Integer> accountLocked = createNumber("accountLocked", Integer.class);

    public final NumberPath<Long> createId = createNumber("createId", Long.class);

    public final DateTimePath<java.util.Date> createTime = createDateTime("createTime", java.util.Date.class);

    public final NumberPath<Integer> credentialsExpired = createNumber("credentialsExpired", Integer.class);

    public final NumberPath<Integer> enabled = createNumber("enabled", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginName = createString("loginName");

    public final StringPath name = createString("name");

    public final NumberPath<Long> operaterId = createNumber("operaterId", Long.class);

    public final DateTimePath<java.util.Date> operaterTime = createDateTime("operaterTime", java.util.Date.class);

    public final QUser parent;

    public final StringPath pwd = createString("pwd");

    public final StringPath remark = createString("remark");

    public final QRole role;

    public final StringPath sex = createString("sex");

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QUser(forProperty("parent"), inits.get("parent")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

