package capstonedesign.medicalproduct.repository.order;

import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.dto.order.OrderSearch;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import capstonedesign.medicalproduct.dto.order.QOrderItemResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.springframework.util.StringUtils.*;


@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public List<OrderItemResponseDto> findAllOrderItemByMemberIdAndOrderInfo(long memberId, OrderSearch orderSearch) {

        QMember m = new QMember("m");
        QItem i = new QItem("i");
        QOrder o = new QOrder("o");
        QOrderItem oi = new QOrderItem("oi");
        QReview r = new QReview("r");

        BooleanBuilder builder = new BooleanBuilder(o.member.id.eq(memberId));

        if(orderSearch.getOrderStatus() != null) {
            builder.and(o.status.eq(orderSearch.getOrderStatus()));
        }

        if(hasText(orderSearch.getItemName())) {
            builder.and(i.name.contains(orderSearch.getItemName()));
        }

        List<OrderItemResponseDto> result = jpaQueryFactory.
                                    select( new QOrderItemResponseDto( i.id, i.name, i.imageSrc, o.orderDate, o.id,
                                            oi.quantity, oi.orderPrice, o.status,

                                            select(r.id)
                                                    .from(r)
                                                    .where(r.member.id.eq(o.member.id)

                                                    .and(r.item.id.eq(i.id)) ) ) ).distinct()

                                    .from(o, oi)
                                    .orderBy(o.orderDate.desc())
                                    .join(oi.order, o)
                                    .join(oi.item, i)
                                    .where(builder)
                                     .fetch();

        return result;
    }

    public List<OrderItemResponseDto> findAllOrderItemById(long orderId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;

        List<OrderItemResponseDto> result = jpaQueryFactory.
                select(new QOrderItemResponseDto(item.id, item.name, item.imageSrc, order.orderDate, order.id,
                        orderItem.quantity, orderItem.orderPrice, order.status)).distinct()

                .from(order, orderItem)
                .orderBy(order.orderDate.desc())
                .join(order.member, member)
                .join(orderItem.order, order)
                .join(orderItem.item, item)
                .where(order.id.eq(orderId))
                .fetch();
        return result;
    }
}