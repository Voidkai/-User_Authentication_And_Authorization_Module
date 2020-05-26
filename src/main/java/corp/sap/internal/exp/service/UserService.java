package corp.sap.internal.exp.service;

import corp.sap.internal.exp.dao.UserDao;
import corp.sap.internal.exp.domain.Privilege;
import corp.sap.internal.exp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserDao userDao;

    public void addUser(String username,String password){
        userDao.addUser(username,password);
    }

    public User getUser(int id){return userDao.getUser(id);}

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    public List<Privilege> getprivByUser(int id){
        return userDao.getprivByUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s)) {
            throw new RuntimeException("用户不能为空");
        }
        User user = userDao.findByUsername(s);
        if(user == null){
            throw new UsernameNotFoundException("not found user:"+s+"'s information.");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(user != null){
            List<Privilege> privilegeLists = userDao.getprivByUser(user.getId());

            privilegeLists.forEach(privilege -> {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(privilege.getPrivilege_code());
                grantedAuthorities.add(grantedAuthority);
            });
        }
        return new User(user.getUsername(),user.getPassword(),grantedAuthorities);
    }
}
