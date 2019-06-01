package edu.ycu.shopping.controller;


import edu.ycu.shopping.entity.Msg;
import edu.ycu.shopping.entity.OrderItem;
import edu.ycu.shopping.entity.OrderRecord;
import edu.ycu.shopping.entity.User;
import edu.ycu.shopping.service.CartService;
import edu.ycu.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    CartService cartService;

    @ResponseBody
    @RequestMapping(value = "/submit_order", method = RequestMethod.POST)
    public Msg insertOrder(@RequestBody OrderRecord orderRecord, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return Msg.fail("请登陆");
        }
        orderRecord.setUserId(user.getId());
        orderService.insertOrderRecord(orderRecord);
        List<OrderItem> orderItems = orderRecord.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItem.setRecordId(orderRecord.getId());
            orderService.insertOrderItem(orderItem);
        }
        cartService.delAllCart(user.getId());
        return Msg.success();
    }
}
