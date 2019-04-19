package edu.ycu.shopping.servicesImpl;

import edu.ycu.shopping.dao.UserMapper;
import edu.ycu.shopping.entity.User;
import edu.ycu.shopping.entity.UserExample;
import edu.ycu.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;

    @Override
    public User login(String username, String password) {
        return userDao.selectByUsernameAndPassword(username, password);
    }


    @Override
    public boolean checkUser(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        long count = userDao.countByExample(example);
        return count == 0;
    }

    @Override
    public int insertUser(User user) {
        int t = userDao.insertSelective(user);
        return t;
    }
}
