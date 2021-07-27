package com.example.demo.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBojProbType is a Querydsl query type for BojProbType
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBojProbType extends EntityPathBase<BojProbType> {

    private static final long serialVersionUID = -1991273144L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBojProbType bojProbType = new QBojProbType("bojProbType");

    public final QBojVo bojVo;

    public final NumberPath<Long> no = createNumber("no", Long.class);

    public final QProbTypeVo probTypeVo;

    public QBojProbType(String variable) {
        this(BojProbType.class, forVariable(variable), INITS);
    }

    public QBojProbType(Path<? extends BojProbType> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBojProbType(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBojProbType(PathMetadata metadata, PathInits inits) {
        this(BojProbType.class, metadata, inits);
    }

    public QBojProbType(Class<? extends BojProbType> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.bojVo = inits.isInitialized("bojVo") ? new QBojVo(forProperty("bojVo")) : null;
        this.probTypeVo = inits.isInitialized("probTypeVo") ? new QProbTypeVo(forProperty("probTypeVo")) : null;
    }

}

