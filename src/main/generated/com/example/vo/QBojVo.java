package com.example.demo.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBojVo is a Querydsl query type for BojVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBojVo extends EntityPathBase<BojVo> {

    private static final long serialVersionUID = -2000701806L;

    public static final QBojVo bojVo = new QBojVo("bojVo");

    public final ListPath<BojProbType, QBojProbType> bojProbType = this.<BojProbType, QBojProbType>createList("bojProbType", BojProbType.class, QBojProbType.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    public final StringPath level = createString("level");

    public final StringPath name = createString("name");

    public final StringPath url = createString("url");

    public QBojVo(String variable) {
        super(BojVo.class, forVariable(variable));
    }

    public QBojVo(Path<? extends BojVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBojVo(PathMetadata metadata) {
        super(BojVo.class, metadata);
    }

}

