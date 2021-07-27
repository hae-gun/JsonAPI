package com.example.demo.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProbTypeVo is a Querydsl query type for ProbTypeVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProbTypeVo extends EntityPathBase<ProbTypeVo> {

    private static final long serialVersionUID = 361917324L;

    public static final QProbTypeVo probTypeVo = new QProbTypeVo("probTypeVo");

    public final ListPath<BojProbType, QBojProbType> bojProbTypes = this.<BojProbType, QBojProbType>createList("bojProbTypes", BojProbType.class, QBojProbType.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath type = createString("type");

    public final StringPath typeEng = createString("typeEng");

    public final NumberPath<Long> typeNo = createNumber("typeNo", Long.class);

    public QProbTypeVo(String variable) {
        super(ProbTypeVo.class, forVariable(variable));
    }

    public QProbTypeVo(Path<? extends ProbTypeVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProbTypeVo(PathMetadata metadata) {
        super(ProbTypeVo.class, metadata);
    }

}

