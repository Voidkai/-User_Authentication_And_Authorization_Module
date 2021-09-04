package corp.sap.internal.exp.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 5953648330227989719L;
    private Integer id;
    private String username;
    private String password;
    private List<GrantedAuthority> privileges;
    private Boolean expired;
    private Boolean locked;
    private String email;
    private String phone;
    private String telephone;

    public User(){
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String email, String phone, String telephone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.telephone = telephone;
    }

    public User(Integer id, String username, String password, List<GrantedAuthority>  permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.privileges = permissions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getPrivileges() {
        return privileges;
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

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
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
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
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
