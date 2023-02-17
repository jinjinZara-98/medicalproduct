package capstonedesign.medicalproduct.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInformation is a Querydsl query type for Information
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QInformation extends BeanPath<Information> {

    private static final long serialVersionUID = 2017668661L;

    public static final QInformation information = new QInformation("information");

    public final StringPath accountHost = createString("accountHost");

    public final StringPath accountNumber = createString("accountNumber");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final StringPath bankName = createString("bankName");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QInformation(String variable) {
        super(Information.class, forVariable(variable));
    }

    public QInformation(Path<? extends Information> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInformation(PathMetadata metadata) {
        super(Information.class, metadata);
    }

}

