package com.john.mapper;

import com.john.bean.RolePermission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(@Param("rId") Integer rId, @Param("pId") Integer pId);

    int insert(RolePermission record);

    List<RolePermission> selectAll();
}