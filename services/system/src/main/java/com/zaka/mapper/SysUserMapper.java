package com.zaka.mapper;

import com.zaka.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     *
     * @param userId
     * @return
     */
    @Select("SELECT * FROM sys_user WHERE user_id = #{user_id}")
    SysUser getUserById(@Param("user_id") int userId);
}
