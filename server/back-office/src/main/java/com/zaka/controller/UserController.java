package com.zaka.controller;

import com.zaka.dto.UserInfoDto;
import com.zaka.entity.SysUser;
import com.zaka.response.Response;
import com.zaka.services.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController("/user")
@Slf4j
public class UserController {

    /**
     *
     */
    @Autowired
    SysUserService sysUserService;

    /**
     *
     */
    @PostMapping("/register")
    public Response registerUser(@Validated @RequestBody UserInfoDto userInfoDto) {
        final SysUser user = this.sysUserService.registerUser(userInfoDto);
        return Response.ok("register successfully.", user);
    }
}
