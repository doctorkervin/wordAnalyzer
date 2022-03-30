package com.lybank.entity.system;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthority is a Querydsl query type for Authority
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthority extends EntityPathBase<Authority> {

    private static final long serialVersionUID = -636169929L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthority authority = new QAuthority("authority");

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QAuthority parent;

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath type = createString("type");

    public QAuthority(String variable) {
        this(Authority.class, forVariable(variable), INITS);
    }

    public QAuthority(Path<? extends Authority> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthority(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthority(PathMetadata metadata, PathInits inits) {
        this(Authority.class, metadata, inits);
    }

    public QAuthority(Class<? extends Authority> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QAuthority(forProperty("parent"), inits.get("parent")) : null;
    }

}

