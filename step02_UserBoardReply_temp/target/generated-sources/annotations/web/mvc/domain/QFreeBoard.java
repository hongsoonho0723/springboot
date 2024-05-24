package web.mvc.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoard is a Querydsl query type for FreeBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoard extends EntityPathBase<FreeBoard> {

    private static final long serialVersionUID = 501303550L;

    public static final QFreeBoard freeBoard = new QFreeBoard("freeBoard");

    public final NumberPath<Long> bno = createNumber("bno", Long.class);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> insertDate = createDateTime("insertDate", java.time.LocalDateTime.class);

    public final StringPath password = createString("password");

    public final NumberPath<Integer> readnum = createNumber("readnum", Integer.class);

    public final ListPath<Reply, QReply> repliesList = this.<Reply, QReply>createList("repliesList", Reply.class, QReply.class, PathInits.DIRECT2);

    public final StringPath subject = createString("subject");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final StringPath writer = createString("writer");

    public QFreeBoard(String variable) {
        super(FreeBoard.class, forVariable(variable));
    }

    public QFreeBoard(Path<? extends FreeBoard> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoard(PathMetadata metadata) {
        super(FreeBoard.class, metadata);
    }

}

