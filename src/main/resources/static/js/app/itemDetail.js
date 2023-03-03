let quantity = $(".quantity_input").val();

$(".plus_btn").on("click", function(){
    $(".quantity_input").val(++quantity);
});

$(".minus_btn").on("click", function(){
    if(quantity > 1){
        $(".quantity_input").val(--quantity);
    }
});

function addItem() {
    alert("상품이 장바구니에 담겼습니다!");
    $(".cart_form").submit();
}

function order() {
    let itemId = $(".individual_ItemId").val();
    let name = $(".individual_Name").val();
    let imageSrc = $(".individual_ImageSrc").val();
    let price = $(".individual_Price").val();
    let quantity = $(".quantity_input").val();

    $(".order_form").find("input[name='orderItemRequestDtos[0].itemId']").val(itemId);
    $(".order_form").find("input[name='orderItemRequestDtos[0].name']").val(name);
    $(".order_form").find("input[name='orderItemRequestDtos[0].imageSrc']").val(imageSrc);
    $(".order_form").find("input[name='orderItemRequestDtos[0].price']").val(price);
    $(".order_form").find("input[name='orderItemRequestDtos[0].quantity']").val(quantity);

    $(".order_form").submit();
}