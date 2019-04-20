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
}
