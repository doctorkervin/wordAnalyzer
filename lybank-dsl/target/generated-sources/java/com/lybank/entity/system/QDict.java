package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDict is a Querydsl query type for Dict
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDict extends EntityPathBase<Dict> {

    private static final long serialVersionUID = -259204798L;

    public static final QDict dict = new QDict("dict");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath key = createString("key");

    public final StringPath value = createString("value");

    public QDict(String variable) {
        super(Dict.class, forVariable(variable));
    }

    public QDict(Path<? extends Dict> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDict(PathMetadata metadata) {
        super(Dict.class, metadata);
    }

}

