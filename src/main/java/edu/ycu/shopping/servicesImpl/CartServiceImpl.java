package edu.ycu.shopping.servicesImpl;

import edu.ycu.shopping.dao.CartMapper;
import edu.ycu.shopping.entity.Cart;
import edu.ycu.shopping.entity.CartExample;
import edu.ycu.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartDao;

    @Override
    public List<Cart> getCartsByUser(int userId) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        cartDao.selectByExample(example);
        return cartDao.selectByExample(example);
    }
}
