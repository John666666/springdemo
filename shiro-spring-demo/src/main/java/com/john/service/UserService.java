package com.john.service;

import com.john.bean.User;

import java.util.Set;

public interface UserService {
    Set<String> getRolesByUsername(String username);

    Set<String> getPermissionsByRoleSet(Set<String> roleSet);

    User findUserByName(String username);
}
