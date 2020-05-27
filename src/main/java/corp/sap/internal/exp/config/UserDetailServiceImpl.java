package corp.sap.internal.exp.config;

import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.PrivilegeService;
import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        User user = userService.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("not found user:"+s+"'s information.");
        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        if(user != null){
//            List<Privilege> privilegeLists = userDao.getprivByUser(user.getId());
//
//            privilegeLists.forEach(privilege -> {
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(privilege.getPrivilege_code());
//                grantedAuthorities.add(grantedAuthority);
//            });
//        }
        return new User(user.getUsername(),user.getPassword());
    }
}
