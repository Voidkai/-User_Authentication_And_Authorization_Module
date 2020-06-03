package corp.sap.internal.exp.domain;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements UserDetails {



    private static final long serialVersionUID = 5953648330227989719L;

    private Integer id;

    private String username;

    private String password;

    private List<GrantedAuthority> privileges;

    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(Integer id,String username, String password,List<GrantedAuthority>  privileges){
        this.id = id;
        this.username = username;
        this.password = password;
        this.privileges = privileges;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPrivileges(List<GrantedAuthority> privileges) {
        this.privileges = privileges;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return privileges;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
