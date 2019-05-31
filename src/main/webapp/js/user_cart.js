var jq = jQuery.noConflict();
jq(function () {
    dealwithCart();
});

function dealwithCart() {
    jq.ajax({
        url: "/cart",
        type: "GET",
        success: function (result) {
            jq("#cars").empty();
            jq("#cart_sum").hide();
            jq("#checkout").hide();
            jq("#cart_count").html("购物车 [ <span>0</span> ]");
            if (result.code == 0) {
                var carts = result.extend.cart;
                if (carts.length == 0) {
                    jq("#cars").append("您尚未购买任何物品，是否进入<a href=\"/\">商品页</a>进行购买！")
                } else {
                    var price_sum = 0;
                    jq.each(carts, function (index, item) {
                        price_sum += item.count * item.good.price;
                        var cartImage = jq("          <div class=\"img\">\n" +
                            "                            <a href=\"javascript:void(0)\">\n" +
                            "                                <img src=\"/images/" + item.good.image + "\" width=\"58\" height=\"58\">\n" +
                            "                            </a>\n" +
                            "                        </div>")
                        var cartName = jq("           <div class=\"name\">\n" +
                            "                            <a href=\"javascript:void(0)\">" + item.good.name + "</a>\n" +
                            "                        </div>")
                        var cartPrice = jq("          <div class=\"price\">\n" +
                            "                            <font color=\"#ff4e00\">￥" + item.good.price + "</font>\&nbsp;X" + item.count + "\n" +
                            "                        </div>")
                        jq("<li></li>").append(cartImage)
                            .append(cartName)
                            .append(cartPrice)
                            .appendTo("#cars")
                        jq("#cart_count").html("购物车 [ <span>" + carts.length + "</span> ]");
                        jq("#cart_sum").html("共计\&nbsp; <font color=\"#ff4e00\">￥</font><span>" + price_sum + "</span>")
                        jq("#checkout").show();
                        jq("#cart_sum").show()
                    })
                }

            }
        }
    })
}

function logout() {
    jq.ajax({
        url: "/logout",
        type: "POST",
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
            }
        }
    })
}

function subToCart(goodId) {
    jq.ajax({
        url: "/cart_sub",
        type: "POST",
        data: "goodId=" + goodId,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
            } else {
                alert(result.msg);
            }
        }
    })
}

function addToCart(goodId) {
    jq.ajax({
        url: "/cart_add",
        type: "POST",
        data: "goodId=" + goodId,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
            } else {
                alert(result.msg);
            }
        }
    })
}

function delAllCarts() {
    jq.ajax({
        url: "/cart_del_all",
        type: "POST",
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
            } else {
                alert(result.msg);
            }
        }
    })
}

function delToCart(goodId) {
    jq.ajax({
        url: "/cart_del",
        type: "POST",
        data: "goodId=" + goodId,
        success: function (result) {
            if (result.code == 0) {
                window.location.reload();
            } else {
                alert(result.msg);
            }
        }
    })
}

function toCartsConfirmAll() {
    window.location.href="/BuyCart_2"
}