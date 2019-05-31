package edu.ycu.shopping.service;

import edu.ycu.shopping.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartsByUser(int userId);

    Cart getCartsByUserAndGood(int userId, int goodId);

    int insertCart(int goodId, int userId);

    int updateCart(Cart cart);

    int delCart(Cart cart);

    int delAllCart(int userId);
}
