package com.john.shiro;

import com.john.bean.Permission;
import com.john.bean.Role;
import com.john.bean.User;
import com.john.bean.dto.ShiroUserDto;
import com.john.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 查询用户权限和角色
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        // 查询用户拥有的角色
        Set<String> roleSet = userService.getRolesByUsername(username);
        System.out.println("查询用户角色：" + roleSet);
        // 查询用户拥有的权限
        Set<String> permissionSet = userService.getPermissionsByRoleSet(roleSet);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserPwdWithCodeToken token = (UserPwdWithCodeToken) authenticationToken;
        String username = (String) token.getPrincipal();
        User user = userService.findUserByName(username);

        ShiroUserDto shiroUserDto = new ShiroUserDto();
        shiroUserDto.setUsername(username);
        shiroUserDto.setPassword(user.getPassword());
        shiroUserDto.setCode("6666");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), shiroUserDto, this.getName());
        return info;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
