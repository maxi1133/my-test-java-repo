package com.zaka.controller;

import com.zaka.annotation.RequestLimit;
import com.zaka.constant.AppConstants;
import com.zaka.dto.LoginInfoDto;
import com.zaka.entity.SysUser;
import com.zaka.response.Response;
import com.zaka.utils.JwtUtils;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    HttpServletRequest request;

    @Autowired
    JwtUtils jwtUtils;

    @PreDestroy
    public void doIt() {
        log.info("destroy bean");
    }

    @PostMapping(value = "/login")
    @RequestLimit(requestLimit = 3)
    public Response login(@Validated @RequestBody final LoginInfoDto userLoginInfo) {

        final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginInfo.getUserName(), userLoginInfo.getPassword());
        final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final SysUser user = (SysUser) this.request.getSession().getAttribute(AppConstants.CURRENT_DB_USER);
        log.info("current session user: {}", user);

        final StringBuilder tokenString = new StringBuilder().append("Bearer ").append(jwtUtils.generateTokenFromUserInfo(user));
        log.info("tokenString: {}", tokenString);

        final Map<String, String> tokenMap = Map.of(AppConstants.TOKEN, tokenString.toString());
        return Response.ok("Login success", tokenMap);
    }

    @GetMapping("/a")
    @RequestLimit(requestLimit = 3)
    public Response a() {
        return Response.ok();
    }
}
