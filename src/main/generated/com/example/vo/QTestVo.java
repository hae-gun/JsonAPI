package com.example.demo.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestVo is a Querydsl query type for TestVo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestVo extends EntityPathBase<TestVo> {

    private static final long serialVersionUID = -1385828049L;

    public static final QTestVo testVo = new QTestVo("testVo");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath url = createString("url");

    public QTestVo(String variable) {
        super(TestVo.class, forVariable(variable));
    }

    public QTestVo(Path<? extends TestVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestVo(PathMetadata metadata) {
        super(TestVo.class, metadata);
    }

}

