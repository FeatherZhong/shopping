package edu.ycu.shopping.service;

import edu.ycu.shopping.entity.OrderItem;
import edu.ycu.shopping.entity.OrderRecord;

public interface OrderService {
    int insertOrderItem(OrderItem orderItem);

    int insertOrderRecord(OrderRecord orderRecord);
}
