package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.DataAccessDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.ServiceTicket;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.Impl.RBACEntityAccessChallenge;
import corp.sap.internal.exp.service.Impl.RBACPermissionChallenge;
import corp.sap.internal.exp.service.exceptions.NoEntityAccessException;
import corp.sap.internal.exp.service.exceptions.NoPermissionException;
import corp.sap.internal.exp.service.exceptions.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserWithPermissionCheckService {

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User changePassword(Integer userId, String password){
        User user = userDao.changePassword(userId, passwordEncoder.encode(password));
        return user;
    }

    public User changeUsername(Integer userId, String username){
        User user = userDao.changeUsername(userId, username);
        return user;
    }
}
