package com.john.web;

import com.john.bean.User;
import com.john.bean.dto.ShiroUserDto;
import com.john.bean.vo.ResponseVO;
import com.john.shiro.UserPwdWithCodeToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public ResponseVO login(ShiroUserDto shiroUser) {
        UserPwdWithCodeToken token = new UserPwdWithCodeToken(shiroUser);
        Subject subject = SecurityUtils.getSubject();
        ResponseVO responseVO = new ResponseVO();
        try {
            subject.login(token);
            responseVO.setCode(200);
            responseVO.setMsg("登录成功！");
        } catch (AuthenticationException e) {
            responseVO.setCode(500);
            responseVO.setMsg("认证失败，请重试！");
        }
        return responseVO;
    }

    @RequestMapping("/logout")
    public ResponseVO logout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            subject.logout();
        }
        return new ResponseVO(200, "退出登录", null);
    }

}
