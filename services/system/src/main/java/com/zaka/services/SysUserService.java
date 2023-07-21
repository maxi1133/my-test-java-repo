package com.zaka.services;

import com.zaka.dto.UserInfoDto;
import com.zaka.entity.SysUser;

public interface SysUserService {

    SysUser getUserByEmailOrUserName(String emailOrUserName);

    SysUser registerUser(UserInfoDto userInfoDto);

}
