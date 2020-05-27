package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(String username, String password) {
        userDao.addUser(username, password);
    }

    public User getUser(int id) {
        return userDao.getUserByID(id);
    }

    public User findByUsername(String username) {
        return userDao.findUserByName(username);
    }

}
