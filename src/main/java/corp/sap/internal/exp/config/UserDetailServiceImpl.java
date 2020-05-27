package corp.sap.internal.exp.config;

import corp.sap.internal.exp.dao.PrivilegeDao;
import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.PrivilegeService;
import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Autowired
    PrivilegeService privilegeService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        User user = userService.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("not found user:" + s + "'s information.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            List privilegeLists = privilegeService.getprivByUser(user.getId());

            for (Object privilegeList : privilegeLists) {
                Map privilegeMap = (Map) privilegeList;
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority((String) privilegeMap.get("privilege_code"));
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
