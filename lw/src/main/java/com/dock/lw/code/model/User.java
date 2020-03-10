package com.dock.lw.code.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dock.lw.common.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;


@ApiModel
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class User extends BaseModel {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "微信openId")
    private String openId;

    @ApiModelProperty(value = "用户类型（1系统用户，2企业用户，3普通用户）")
    private String userType;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别（1男，2女，0未知）")
    private String sex;

    @ApiModelProperty(value = "语言，简体中文为zh_CN")
    private String language;

    @ApiModelProperty(value = "所在城市")
    private String city;

    @ApiModelProperty(value = "所在省份")
    private String province;

    @ApiModelProperty(value = "所在国家")
    private String country;

    @ApiModelProperty(value = "unionId")
    private String unionId;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @TableField(exist = false)
    @ApiModelProperty(value = "token", hidden = true)
    private String token;


}
