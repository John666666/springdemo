package com.john.service.impl;

import com.john.bean.RolePermission;
import com.john.bean.User;
import com.john.mapper.PermissionMapper;
import com.john.mapper.RolePermissionMapper;
import com.john.mapper.UserMapper;
import com.john.mapper.UserRoleMapper;
import com.john.service.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Setter
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> getRolesByUsername(String username) {
        Set<String> roles = userMapper.selectRoles(username);
        return roles;
    }

    @Override
    public Set<String> getPermissionsByRoleSet(Set<String> roleSet) {

        return null;
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.selectByName(username);
    }


}
