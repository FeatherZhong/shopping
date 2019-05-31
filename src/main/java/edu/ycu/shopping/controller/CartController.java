package edu.ycu.shopping.controller;

import edu.ycu.shopping.entity.Cart;
import edu.ycu.shopping.entity.Good;
import edu.ycu.shopping.entity.Msg;
import edu.ycu.shopping.entity.User;
import edu.ycu.shopping.service.CartService;
import edu.ycu.shopping.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/BuyCart_1", method = RequestMethod.GET)
    public String toCart1() {
        return "buycart_1";
    }

    @RequestMapping(value = "/BuyCart_2", method = RequestMethod.GET)
    public String toCart2() {
        return "buycart_2";
    }

    @RequestMapping(value = "/BuyCart_3", method = RequestMethod.GET)
    public String toCart3() {
        return "buycart_3";
    }

    @ResponseBody
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public Msg getCarts(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail();
        } else {
            List<Cart> carts = cartService.getCartsByUser(user.getId());
            return Msg.success().add("cart", carts);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/cart_sub", method = RequestMethod.POST)
    public Msg subCartGood(int goodId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.getCartsByUserAndGood(user.getId(), goodId);
        if (cart.getCount() == 1) {
            cartService.delCart(cart);
            return Msg.success();
        } else {
            cart.setCount(cart.getCount() - 1);
            if (cartService.updateCart(cart) > 0) {
                return Msg.success();
            } else {
                return Msg.fail();
            }
        }
    }

    @ResponseBody
    @RequestMapping(value = "/cart_add", method = RequestMethod.POST)
    public Msg addCartGood(int goodId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail("请登陆");
        }
        Cart cart = cartService.getCartsByUserAndGood(user.getId(), goodId);
        if (cart == null) {
            cartService.insertCart(goodId, user.getId());
            return Msg.success();
        }
        cart.setCount(cart.getCount() + 1);
        if (cartService.updateCart(cart) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/cart_del", method = RequestMethod.POST)
    @ResponseBody
    public Msg delCartGood(int goodId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Cart cart = cartService.getCartsByUserAndGood(user.getId(), goodId);
        if (cartService.delCart(cart) > 0) {
            return Msg.success();
        } else {
            return Msg.fail();
        }
    }

    @RequestMapping(value = "/cart_del_all", method = RequestMethod.POST)
    @ResponseBody
    public Msg delCartGoods(HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartService.delAllCart(user.getId());
        return Msg.success();
    }
}
