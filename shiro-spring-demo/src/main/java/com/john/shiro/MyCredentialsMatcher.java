package com.john.shiro;

import com.john.bean.dto.ShiroUserDto;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

/**
 * 自定义口令匹配， 检查密码和验证码是否匹配
 */
public class MyCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        // 用户输入的口令
        UserPwdWithCodeToken _token = (UserPwdWithCodeToken) token;
        ShiroUserDto inputToken = (ShiroUserDto) _token.getCredentials();

        // 从数据库中查询出来的token
        ShiroUserDto dbToken = (ShiroUserDto) info.getCredentials();

        // 检查验证码
        if(!dbToken.getCode().equalsIgnoreCase(inputToken.getCode())) {
            return false;
        }
        if(!dbToken.getPassword().equals(inputToken.getPassword())) {
            return false;
        }
        return true;
    }
}
