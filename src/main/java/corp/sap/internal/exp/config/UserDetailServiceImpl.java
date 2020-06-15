package corp.sap.internal.exp.config;

import corp.sap.internal.exp.domain.User;
import corp.sap.internal.exp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

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
        return new User(user.getId(),user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
