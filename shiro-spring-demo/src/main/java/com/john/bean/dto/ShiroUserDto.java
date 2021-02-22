package com.john.bean.dto;

import lombok.Data;

/**
 * 封装用户的口令认证信息，包括用户名、密码和验证码
 *
 */
@Data
public class ShiroUserDto {
    private String username;
    private String password;
    private String code;
}
