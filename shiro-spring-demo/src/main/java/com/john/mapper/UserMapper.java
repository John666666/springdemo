package com.john.mapper;

import com.john.bean.User;
import java.util.List;
import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    Set<String> selectRoles(String username);

    User selectByName(String username);
}