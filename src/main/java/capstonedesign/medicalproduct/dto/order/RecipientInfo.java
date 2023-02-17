package capstonedesign.medicalproduct.dto.order;

import capstonedesign.medicalproduct.domain.entity.Order;
import lombok.Data;


@Data
public class RecipientInfo {

    private String recipientName;

    private String recipientPhoneNumber;

    private String recipientAddress;

    private String recipientAddressDetail;

    private String orderAccountHost;

    private String orderBankName;

    private String orderAccountNumber;

    private String deliveryMessage;

    public RecipientInfo(Order order) {
        this.recipientName = order.getInformation().getName();
        this.recipientPhoneNumber = order.getInformation().getPhoneNumber();
        this.recipientAddress = order.getInformation().getAddress();
        this.recipientAddressDetail = order.getInformation().getAddressDetail();
        this.orderAccountHost = order.getInformation().getAccountHost();
        this.orderBankName = order.getInformation().getBankName();
        this.orderAccountNumber = order.getInformation().getAccountNumber();
        this.deliveryMessage = order.getDeliveryMessage();
    }
}
