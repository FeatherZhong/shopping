package edu.ycu.shopping.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.ycu.shopping.entity.Good;
import edu.ycu.shopping.entity.Msg;
import edu.ycu.shopping.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GoodController {
    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String toSearch() {
        return "searchresult";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public Msg search(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", defaultValue = "1") int page) {
        PageHelper.startPage(page, 12);
        List<Good> goods = goodService.search(keyword);
        PageInfo<Good> pageInfo = new PageInfo<>(goods, 5);
        return Msg.success().add("pageInfo", pageInfo);
    }
}
