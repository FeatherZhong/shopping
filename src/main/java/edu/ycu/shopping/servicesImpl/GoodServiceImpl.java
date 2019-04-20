package edu.ycu.shopping.servicesImpl;

import edu.ycu.shopping.dao.GoodMapper;
import edu.ycu.shopping.entity.Good;
import edu.ycu.shopping.entity.GoodExample;
import edu.ycu.shopping.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Autowired
    GoodMapper goodDao;

    @Override
    public List<Good> search(String keyword) {
        GoodExample example = new GoodExample();
        GoodExample.Criteria criteria = example.createCriteria();
        criteria.andNameLike('%' + keyword + '%');
        return goodDao.selectByExample(example);
    }

    @Override
    public Good selectById(int id) {
        return goodDao.selectByPrimaryKey(id);
    }
}
