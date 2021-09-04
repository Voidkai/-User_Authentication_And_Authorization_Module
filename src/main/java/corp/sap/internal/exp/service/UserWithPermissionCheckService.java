package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserWithPermissionCheckService {

    @Autowired
    private PrivilegeCheckService privilegeCheckService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User updatePassword(Integer userId, User user){
        return userDao.updatePassword(userId, passwordEncoder.encode(user.getPassword()));
    }

    public User updateUsername(Integer userId, User user){
        return userDao.updateUsername(userId, user.getUsername());
    }
}
