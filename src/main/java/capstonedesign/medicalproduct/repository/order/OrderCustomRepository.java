package capstonedesign.medicalproduct.repository.order;

import capstonedesign.medicalproduct.dto.item.OrderSearch;
import capstonedesign.medicalproduct.dto.order.OrderItemResponseDto;
import java.util.List;

public interface OrderCustomRepository {
    List<OrderItemResponseDto> findAllOrderItemById(long orderId);
    List<OrderItemResponseDto> findAllOrderItemByMemberIdAndOrderInfo(long memberId, OrderSearch orderSearch);
}
