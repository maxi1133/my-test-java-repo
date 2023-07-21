package com.zaka.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zaka.annotation.Excel;
import com.zaka.constant.AppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class SysUser  {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    @TableField("user_name")
    @Excel
    private String userName;

    @TableField("email")
    @Excel
    private String email;

    @TableField("password")
    private String password;

    @TableField("role")
    @Excel
    private String role;
}

