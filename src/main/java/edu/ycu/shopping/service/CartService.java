package edu.ycu.shopping.service;

import edu.ycu.shopping.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getCartsByUser(int userId);
}
