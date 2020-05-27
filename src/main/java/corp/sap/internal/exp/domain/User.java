package corp.sap.internal.exp.domain;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class User implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private Set<SimpleGrantedAuthority> privileges;

    public User(){
    }

    public User(String username, String password, Collection<? extends  GrantedAuthority> privileges){
        this.username = username;
        this.password = password;
        this.privileges = (Set<SimpleGrantedAuthority>) privileges;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
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
