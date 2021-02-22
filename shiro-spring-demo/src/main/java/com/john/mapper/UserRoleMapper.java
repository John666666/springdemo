package com.john.mapper;

import com.john.bean.UserRole;
import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    List<UserRole> selectAll();
}