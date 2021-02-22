package com.john.shiro;

import com.john.bean.dto.ShiroUserDto;
import org.apache.shiro.authc.AuthenticationToken;

public class UserPwdWithCodeToken implements AuthenticationToken {

    private ShiroUserDto shiroUser;

    public UserPwdWithCodeToken(ShiroUserDto shiroUser) {
        this.shiroUser = shiroUser;
    }

    @Override
    public Object getPrincipal() {
        return shiroUser.getUsername();
    }

    @Override
    public Object getCredentials() {
        return shiroUser;
    }


}
