function cartPut(itemId) {
  alert("상품이 장바구니에 담겼습니다!");
  var form = document.createElement("form");
  form.setAttribute("method", "post");
  form.setAttribute("action", "/cart/" + itemId + "/itemInstantPut");
  document.body.appendChild(form);
  form.submit();
}