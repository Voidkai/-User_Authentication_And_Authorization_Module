package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getUser(Integer id) {
        return userDao.getUserByID(id);
    }

    public User findByUsername(String username) {
        return userDao.findUserByName(username);
    }

    public List<String> getAllUsername(){ return userDao.getAllUsername();}


}
