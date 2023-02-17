$(document).ready(function(){

    setTotalInfo()

});

$(".all_check_input").on("click", function(){

    if($(".all_check_input").prop("checked")){
        $(".individual_cart_checkbox").attr("checked", true);
    } else{
        $(".individual_cart_checkbox").attr("checked", false);
    }

	setTotalInfo($(".cart_info_td"));
})

$(".individual_cart_checkbox").on("change", function(){
	setTotalInfo($(".cart_info_td"));
});

function setTotalInfo(){

    let totalCount = 0;
	let totalKind = 0;
    let totalPrice = 0;

    $(".cart_info_td").each(function(index, element){

        if($(element).find(".individual_cart_checkbox").is(":checked") === true){
            totalCount += parseInt($(element).find(".individual_Quantity").val());

            totalKind += 1;

            totalPrice += parseInt($(element).find(".individual_TotalPrice").val());
		}

	});

	$(".totalCount_span").text(totalCount);

	$(".totalKind_span").text(totalKind);

	$(".totalPrice_span").text(totalPrice.toLocaleString());
}

function cancel(id) {
     var form = document.createElement("form");
     form.setAttribute("method", "post");
     form.setAttribute("action", "/cart/" + id + "/cancel");
     document.body.appendChild(form);
     form.submit();
     alert("장바구니에서 상품이 삭제되었습니다!");
}

function order() {

	let form_contents ='';

	let orderNumber = 0;

	$(".cart_info_td").each(function(index, element){

		if($(element).find(".individual_cart_checkbox").is(":checked") == true){

			let cartId = $(element).find(".individual_CartId").val();
			let itemId = $(element).find(".individual_ItemId").val();
			let name = $(element).find(".individual_Name").val();
			let imageSrc = $(element).find(".individual_ImageSrc").val();
			let quantity = $(element).find(".individual_Quantity").val();
            let price = $(element).find(".individual_Price").val();

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

			orderNumber += 1;
		}
	});

	$(".order_form").html(form_contents);
	$(".order_form").submit();
}