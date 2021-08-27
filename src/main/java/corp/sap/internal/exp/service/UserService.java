package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.Impl.RBACPermissionChallenge;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataAccessDao dataAccessDao;

    public User getUser(Integer id) {
        return userDao.getUserByID(id);
    }

    public User findByUsername(String username) {
        return userDao.findUserByName(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User register(String username, String password) throws NotSupportedException, NoPermissionException {
        User user = userDao.addUser(username, password);
        return user;
    }
}
