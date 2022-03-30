package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoogleV is a Querydsl query type for GoogleV
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoogleV extends EntityPathBase<GoogleV> {

    private static final long serialVersionUID = -1068818383L;

    public static final QGoogleV googleV = new QGoogleV("googleV");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath qrcodeStr = createString("qrcodeStr");

    public final StringPath secret = createString("secret");

    public final StringPath status = createString("status");

    public final StringPath userName = createString("userName");

    public QGoogleV(String variable) {
        super(GoogleV.class, forVariable(variable));
    }

    public QGoogleV(Path<? extends GoogleV> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoogleV(PathMetadata metadata) {
        super(GoogleV.class, metadata);
    }

}

