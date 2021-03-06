package edu.ycu.shopping.controller;

import edu.ycu.shopping.entity.Cart;
import edu.ycu.shopping.entity.Msg;
import edu.ycu.shopping.entity.User;
import edu.ycu.shopping.service.CartService;
import edu.ycu.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/regist", method = RequestMethod.GET)
    public String toRegist() {
        return "regist";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String toMember() {
        return "member";
    }

    @RequestMapping(value = "/member_order", method = RequestMethod.GET)
    public String toMemberOrder() {
        return "member_order";
    }

    @ResponseBody
    @RequestMapping("/checkUser")
    public Msg checkUser(@RequestParam("username") String username) {
        boolean b = userService.checkUser(username);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("message", "用户名不可用");
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Msg login(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return Msg.success();
        } else {
            return Msg.fail().add("message", "用户名或密码错误");
        }
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public Msg insertUser(@RequestBody User user) {
        boolean b = userService.checkUser(user.getUsername());
        if (b) {
            userService.insertUser(user);
            return Msg.success();
        } else {
            return Msg.fail().add("message", "用户名不可用");
        }
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public Msg logout(HttpSession session) {
        session.removeAttribute("user");
        return Msg.success();
    }
}
