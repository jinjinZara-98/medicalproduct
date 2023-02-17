$(document).ready(function(){

    let totalCount = 0;
	let totalKind = 0;
    let totalPrice = 0;

    $(".order_info_td").each(function(index, element){

		totalCount += parseInt($(element).find(".individual_Quantity").val());

		totalKind += 1;

		totalPrice += parseInt($(element).find(".individual_TotalPrice").val());
	});

	$(".totalCount_span").text(totalCount);

	$(".totalKind_span").text(totalKind);

	$(".totalPrice_span").text(totalPrice.toLocaleString());
});

function order() {

	let form_contents ='';

	let orderNumber = 0;

    let name = $("#memberName").val();
    let name_input = "<input name = 'name' type='hidden' value='" + name + "'>";
    form_contents += name_input;

    let phoneNumber = $("#phoneNumber").val();
    let phoneNumber_input = "<input name = 'phoneNumber' type='hidden' value='" + phoneNumber + "'>";
    form_contents += phoneNumber_input;

    let address = $("#address").val();
    let address_input = "<input name = 'address' type='hidden' value='" + address + "'>";
    form_contents += address_input;

    let addressDetail = $("#addressDetail").val();
    let addressDetail_input = "<input name = 'addressDetail' type='hidden' value='" + addressDetail + "'>";
    form_contents += addressDetail_input;

    let deliveryMessage = $("#deliveryMessage option:selected").val();
    let deliveryMessage_input = "<input name = 'deliveryMessage' type='hidden' value='" + deliveryMessage + "'>";
    form_contents += deliveryMessage_input;

    let accountHost = $("#accountHost").val();
    let accountHost_input = "<input name = 'accountHost' type='hidden' value='" + accountHost + "'>";
    form_contents += accountHost_input;

    let bankName = $("#bankName option:selected").val();
    let bankName_input = "<input name = 'bankName' type='hidden' value='" + bankName + "'>";
    form_contents += bankName_input;

    let accountNumber = $("#accountNumber").val();
    let accountNumber_input = "<input name = 'accountNumber' type='hidden' value='" + accountNumber + "'>";
    form_contents += accountNumber_input;

	$(".order_info_td").each(function(index, element){

          let cartId = $(element).find(".individual_CartId").val();
          let itemId = $(element).find(".individual_ItemId").val();
          let name = $(element).find(".individual_Name").val();
          let imageSrc = $(element).find(".individual_ImageSrc").val();
          let quantity = $(element).find(".individual_Quantity").val();
          let price = $(element).find(".individual_Price").val();
          let totalPrice = $(element).find(".individual_TotalPrice").val();

          let cartId_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].cartId' type='hidden' value='" + cartId + "'>";
          form_contents += cartId_input;

          let itemId_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].itemId' type='hidden' value='" + itemId + "'>";
          form_contents += itemId_input;

          let name_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].name' type='hidden' value='" + name + "'>";
          form_contents += name_input;

          let imageSrc_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].imageSrc' type='hidden' value='" + imageSrc + "'>";
          form_contents += imageSrc_input;

          let quantity_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].quantity' type='hidden' value='" + quantity + "'>";
          form_contents += quantity_input;

          let price_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].price' type='hidden' value='" + price + "'>";
          form_contents += price_input;

          let totalPrice_input = "<input name = 'orderItemRequestDtos[" + orderNumber + "].totalPrice' type='hidden' value='" + totalPrice + "'>";
          form_contents += totalPrice_input;

          orderNumber += 1;

	});

	$(".order_form").html(form_contents);
	$(".order_form").submit();

}