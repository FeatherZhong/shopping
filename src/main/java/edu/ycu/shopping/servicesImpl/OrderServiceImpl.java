package edu.ycu.shopping.servicesImpl;

import edu.ycu.shopping.dao.OrderItemMapper;
import edu.ycu.shopping.dao.OrderRecordMapper;
import edu.ycu.shopping.entity.OrderItem;
import edu.ycu.shopping.entity.OrderRecord;
import edu.ycu.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderItemMapper orderItemDao;

    @Autowired
    OrderRecordMapper orderRecordDao;

    @Override
    public int insertOrderItem(OrderItem orderItem) {
        return orderItemDao.insertSelective(orderItem);
    }

    @Override
    public int insertOrderRecord(OrderRecord orderRecord) {
        return orderRecordDao.insertSelective(orderRecord);
    }
}
