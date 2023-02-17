package capstonedesign.medicalproduct.dto.item;

import capstonedesign.medicalproduct.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class OrderSearch {

    private String itemName;

    private OrderStatus orderStatus;
}

