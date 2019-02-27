package com.example.demo.bo;

import com.example.demo.entity.AdminUser;
import com.example.demo.entity.PrFuncs;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dhf_ndm on 2019/2/21
 * the previous bug derived from the previous
 */
public class UserDetail implements UserDetails {

    private AdminUser adminUser;

    private List<PrFuncs> prFuncs;

    public UserDetail(){}

    public UserDetail(AdminUser adminUser,List<PrFuncs> prFuncs){
        this.adminUser = adminUser;
        this.prFuncs = prFuncs;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return prFuncs.stream()
                .map(prFunc->{return  new SimpleGrantedAuthority(prFunc.getFuncValue());})
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.adminUser.getUsername();
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
        return this.adminUser.getStatus().equals(1);
    }
}
