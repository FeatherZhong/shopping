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
        return cartDao.selectByExample(example);
    }

    @Override
    public Cart getCartsByUserAndGood(int userId, int goodId) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andGoodIdEqualTo(goodId);
        List<Cart> carts = cartDao.selectByExample(example);
        if (carts.isEmpty()) {
            return null;
        } else {
            return carts.get(0);
        }
    }

    @Override
    public int insertCart(int goodId, int userId) {
        return cartDao.insert(goodId, userId);
    }

    @Override
    public int updateCart(Cart cart) {
        return cartDao.updateByPrimaryKey(cart);
    }

    @Override
    public int delCart(Cart cart) {
        return cartDao.deleteByPrimaryKey(cart.getId());
    }

    @Override
    public int delAllCart(int userId) {
        CartExample example = new CartExample();
        CartExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return cartDao.deleteByExample(example);
    }
}
