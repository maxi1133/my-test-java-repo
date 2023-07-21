package com.zaka.services.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zaka.constant.AppConstants;
import com.zaka.dto.UserInfoDto;
import com.zaka.entity.SysUser;
import com.zaka.mapper.SysUserMapper;
import com.zaka.services.SysUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService, UserDetailsService {

    /**
     *
     */
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     *
     */
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     *
     */
    @Autowired
    HttpServletRequest request;

    /**
     *
     */
    @Override
    public SysUser getUserByEmailOrUserName(final String emailUserName) {
        Assert.isTrue(StringUtils.isNotBlank(emailUserName), "email or username must not be null.");

        LambdaQueryWrapper<SysUser> lqw = new LambdaQueryWrapper<SysUser>()
                .select(SysUser::getUserId, SysUser::getUserName, SysUser::getPassword, SysUser::getEmail)
                .eq(SysUser::getUserName, emailUserName)
                .or()
                .eq(SysUser::getEmail, emailUserName);
        return this.sysUserMapper.selectOne(lqw);
    }

    /**
     *
     */
    @Override
    public SysUser registerUser(final UserInfoDto userInfoDto) {
        Assert.notNull(userInfoDto, "User's info must not be null.");
        Assert.isTrue(userInfoDto.getPassword().equals(userInfoDto.getConfirmPassword()), "Confirmed password must equal to password.");

        SysUser existedUser = this.getUserByEmailOrUserName(userInfoDto.getUserName());
        Assert.isNull(existedUser, "Username is existed.");
        existedUser = this.getUserByEmailOrUserName(userInfoDto.getEmail());
        Assert.isNull(existedUser, "Email is existed.");

        final SysUser sysUser = SysUser.builder()
                .userName(userInfoDto.getUserName())
                .email(userInfoDto.getEmail())
                .password(passwordEncoder.encode(userInfoDto.getPassword()))
                .build();

        this.sysUserMapper.saveOrUpdate(sysUser);
        sysUser.setPassword(null);
        return sysUser;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final SysUser user = this.getUserByEmailOrUserName(username);
        Assert.notNull(user, "user is not existed.");

        user.setRole(AppConstants.ROLE_ADMIN);
        this.request.getSession().setAttribute(AppConstants.CURRENT_DB_USER, user);

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(AppConstants.ROLE_ADMIN)
                .build();
    }
}
